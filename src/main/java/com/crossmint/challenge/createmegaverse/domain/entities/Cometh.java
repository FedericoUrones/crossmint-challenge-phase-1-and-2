package com.crossmint.challenge.createmegaverse.domain.entities;

import lombok.Data;

@Data
public class Cometh extends SpaceElement{
    private EntitiesDirections direction;

    public Cometh(Integer row, Integer column, EntitiesDirections direction) {
        super(row, column);
        this.direction = direction;
    }
}
