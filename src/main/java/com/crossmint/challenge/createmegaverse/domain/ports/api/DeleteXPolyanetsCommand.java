package com.crossmint.challenge.createmegaverse.domain.ports.api;

import com.crossmint.challenge.createmegaverse.domain.entities.SizeAndMargin;

public interface DeleteXPolyanetsCommand {
    void execute(SizeAndMargin sizeAndMargin) throws Exception;
}
