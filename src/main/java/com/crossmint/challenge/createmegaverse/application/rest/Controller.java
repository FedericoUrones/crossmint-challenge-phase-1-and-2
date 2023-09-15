package com.crossmint.challenge.createmegaverse.application.rest;

import com.crossmint.challenge.createmegaverse.application.rest.entities.Status;
import com.crossmint.challenge.createmegaverse.application.rest.mapper.SizeAndMarginMapper;
import com.crossmint.challenge.createmegaverse.domain.ports.api.CreateXCommand;
import com.crossmint.challenge.createmegaverse.application.rest.entities.SizeAndMarginRequest;
import com.crossmint.challenge.createmegaverse.domain.ports.api.DeleteXPolyanetsCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private CreateXCommand createXCommand;

    @Autowired
    private DeleteXPolyanetsCommand deleteXPolyanetsCommand;

    @Autowired
    private SizeAndMarginMapper sizeAndMarginMapper;

    @PostMapping("/createPolyanetsX")
    public ResponseEntity<Status> createX(@RequestBody SizeAndMarginRequest sizeAndMarginRequest) {

        try {
            createXCommand.execute(sizeAndMarginMapper.requestToDomain(sizeAndMarginRequest));
        } catch (Exception e) {
            return new ResponseEntity<Status>(new Status(false, "Error happened"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Status>(new Status(true, null), HttpStatus.OK);
    }

    @DeleteMapping("/deletePolyanetsX")
    public ResponseEntity<Status> deleteX(@RequestBody SizeAndMarginRequest sizeAndMarginRequest) {

        try {
            deleteXPolyanetsCommand.execute(sizeAndMarginMapper.requestToDomain(sizeAndMarginRequest));
        } catch (Exception e) {
            return new ResponseEntity<Status>(new Status(false, "Error happened"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Status>(new Status(true, null), HttpStatus.OK);
    }
}
