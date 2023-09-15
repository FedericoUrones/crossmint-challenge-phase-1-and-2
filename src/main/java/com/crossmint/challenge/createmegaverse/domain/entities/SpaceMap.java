package com.crossmint.challenge.createmegaverse.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SpaceMap {
    private List<Polyanet> polyanetList;
    private List<Soloon> soloonList;
    private List<Cometh> comethList;
}
