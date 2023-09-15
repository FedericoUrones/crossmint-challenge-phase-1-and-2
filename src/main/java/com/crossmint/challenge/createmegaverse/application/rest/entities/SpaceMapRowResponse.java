package com.crossmint.challenge.createmegaverse.application.rest.entities;

import com.crossmint.challenge.createmegaverse.domain.entities.SpaceElement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SpaceMapRowResponse {
    private List<SpaceElement> columns;
}
