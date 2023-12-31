package com.crossmint.challenge.createmegaverse.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SpaceMapRow {
    private List<SpaceElement> columns;
}
