package com.crossmint.challenge.createmegaverse.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SpaceMap {
    private List<SpaceMapRow> rows;
}
