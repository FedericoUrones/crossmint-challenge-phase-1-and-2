package com.crossmint.challenge.createmegaverse.domain.ports.api;

import com.crossmint.challenge.createmegaverse.domain.entities.XCreator;

public interface CreateXCommand {
    void execute(XCreator xCreator);
}
