package com.crossmint.challenge.createmegaverse.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class SpaceMapRow {
    private List<SpaceElement> columns;
}
