package com.crossmint.challenge.createmegaverse.domain.entities;

import lombok.Data;

@Data
public class Soloon extends SpaceElement{
    private EntitiesColors color;

    public Soloon(Integer row, Integer column, EntitiesColors color) {
        super(row, column);
        this.color = color;
    }
}
