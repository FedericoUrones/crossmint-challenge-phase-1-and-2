package com.crossmint.challenge.createmegaverse.application.rest;

import com.crossmint.challenge.createmegaverse.application.rest.entities.SpaceMapResponse;
import com.crossmint.challenge.createmegaverse.application.rest.entities.Status;
import com.crossmint.challenge.createmegaverse.application.rest.mapper.SizeAndMarginMapper;
import com.crossmint.challenge.createmegaverse.application.rest.mapper.SpaceMapMapper;
import com.crossmint.challenge.createmegaverse.domain.ports.api.CreateCrossmintLogoCommand;
import com.crossmint.challenge.createmegaverse.domain.ports.api.CreateXCommand;
import com.crossmint.challenge.createmegaverse.application.rest.entities.SizeAndMarginRequest;
import com.crossmint.challenge.createmegaverse.domain.ports.api.DeleteXPolyanetsCommand;
import com.crossmint.challenge.createmegaverse.domain.ports.api.GetGoalMapQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private CreateXCommand createXCommand;

    @Autowired
    private DeleteXPolyanetsCommand deleteXPolyanetsCommand;

    @Autowired
    private CreateCrossmintLogoCommand createCrossmintLogoCommand;

    @Autowired
    private GetGoalMapQuery getGoalMapQuery;

    @Autowired
    private SizeAndMarginMapper sizeAndMarginMapper;

    @Autowired
    private SpaceMapMapper spaceMapMapper;

    @PostMapping("/polyanetsX")
    public ResponseEntity<Status> createX(@RequestBody SizeAndMarginRequest sizeAndMarginRequest) {

        try {
            createXCommand.execute(sizeAndMarginMapper.requestToDomain(sizeAndMarginRequest));
        } catch (Exception e) {
            return new ResponseEntity<Status>(new Status(false, "Error happened while creating Polyanets X :-("), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Status>(new Status(true, null), HttpStatus.OK);
    }

    @DeleteMapping("/polyanetsX")
    public ResponseEntity<Status> deleteX(@RequestBody SizeAndMarginRequest sizeAndMarginRequest) {

        try {
            deleteXPolyanetsCommand.execute(sizeAndMarginMapper.requestToDomain(sizeAndMarginRequest));
        } catch (Exception e) {
            return new ResponseEntity<>(new Status(false, "Error happened while deleting Polyanets X :-("), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new Status(true, null), HttpStatus.OK);
    }

    @GetMapping("/goal")
    public ResponseEntity getGoalsMap() {
        SpaceMapResponse spaceMapResult;
        try {
            spaceMapResult = spaceMapMapper.domainToResponse(getGoalMapQuery.execute());
        } catch (Exception e) {
            return new ResponseEntity<>(new Status(false, "Error happened while getting goals map :-("), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(spaceMapResult, HttpStatus.OK);
    }

    @PostMapping("/crossmint-logo")
    public ResponseEntity createCrossmintLogo() {
        try {
            createCrossmintLogoCommand.execute();
        } catch (Exception e) {
            return new ResponseEntity<>(new Status(false, "Error happened while creating logo :-("), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new Status(true, null), HttpStatus.OK);
    }
}
