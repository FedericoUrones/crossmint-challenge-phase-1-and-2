package com.crossmint.challenge.createmegaverse.domain.ports.spi;

import com.crossmint.challenge.createmegaverse.domain.entities.Polyanet;

public interface DeletePolyanetsPort {
    void deletePolyanet(Polyanet polyanet);
}
