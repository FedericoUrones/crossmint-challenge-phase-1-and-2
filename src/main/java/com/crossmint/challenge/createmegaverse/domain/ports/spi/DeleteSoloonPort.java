package com.crossmint.challenge.createmegaverse.domain.ports.spi;

import com.crossmint.challenge.createmegaverse.domain.entities.Soloon;

public interface DeleteSoloonPort {
    void deleteSoloon(Soloon soloon);
}
