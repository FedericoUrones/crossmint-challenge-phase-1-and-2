package com.crossmint.challenge.createmegaverse.application.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Status {
    private Boolean isOk;
    private String message;
}
