package com.crossmint.challenge.createmegaverse.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class SpaceElement {
    private Integer row;
    private Integer column;
}
