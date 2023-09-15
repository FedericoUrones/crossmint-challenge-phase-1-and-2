package com.crossmint.challenge.createmegaverse.domain.usecase;

import com.crossmint.challenge.createmegaverse.domain.entities.SpaceMap;
import com.crossmint.challenge.createmegaverse.domain.ports.api.GetGoalMapQuery;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.GetGoalMapPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetGoalMap implements GetGoalMapQuery {

    @Autowired
    private GetGoalMapPort getGoalMapPort;

    @Override
    public SpaceMap execute() {
        return getGoalMapPort.getGoalMap();
    }
}
