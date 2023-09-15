package com.crossmint.challenge.createmegaverse.application.rest.entities;

import com.crossmint.challenge.createmegaverse.domain.entities.Cometh;
import com.crossmint.challenge.createmegaverse.domain.entities.Polyanet;
import com.crossmint.challenge.createmegaverse.domain.entities.Soloon;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SpaceMapResponse {
    private List<Polyanet> polyanetList;
    private List<Soloon> soloonList;
    private List<Cometh> comethList;
}
