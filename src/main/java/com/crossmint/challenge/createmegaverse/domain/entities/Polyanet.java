package com.crossmint.challenge.createmegaverse.domain.entities;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Polyanet extends SpaceElement {
    public Polyanet(int row, int column) {
        this.setRow(row);
        this.setColumn(column);
    }
}
