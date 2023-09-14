package com.crossmint.challenge.createmegaverse.application.rest;

import com.crossmint.challenge.createmegaverse.application.rest.mapper.XCreatorMapper;
import com.crossmint.challenge.createmegaverse.domain.ports.api.CreateXCommand;
import com.crossmint.challenge.createmegaverse.application.rest.entities.XCreatorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XCreatorController {

    @Autowired
    private CreateXCommand createXCommand;

    @Autowired
    private XCreatorMapper xCreatorMapper;

    @PostMapping("/createX")
    public ResponseEntity createX(@RequestBody XCreatorRequest xCreatorRequest) {

        createXCommand.execute(xCreatorMapper.requestToDomain(xCreatorRequest));

        return new ResponseEntity(null, HttpStatus.OK);
    }
}
