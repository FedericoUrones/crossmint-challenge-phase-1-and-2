package com.crossmint.challenge.createmegaverse.domain.ports.spi;

import com.crossmint.challenge.createmegaverse.domain.entities.Cometh;

public interface DeleteComethPort {
    void deleteCometh(Cometh cometh);
}
