package com.crossmint.challenge.createmegaverse.domain.usecase;

import com.crossmint.challenge.createmegaverse.domain.entities.SpaceMap;
import com.crossmint.challenge.createmegaverse.domain.ports.api.CreateCrossmintLogoCommand;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.CreateComethPort;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.CreatePolyanetPort;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.CreateSoloonPort;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.GetGoalMapPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCrossmintLogo implements CreateCrossmintLogoCommand {

    Logger logger = LoggerFactory.getLogger(CreateCrossmintLogo.class);

    @Autowired
    private GetGoalMapPort getGoalMapPort;

    @Autowired
    private CreatePolyanetPort createPolyanetPort;

    @Autowired
    private CreateSoloonPort createSoloonPort;

    @Autowired
    private CreateComethPort createComethPort;


    @Override
    public void execute() throws Exception {

        SpaceMap requiredSpaceMap = getGoalMapPort.getGoalMap();
        logger.info("Got required goal map");

        logger.info("Creating Polyanets...");
        requiredSpaceMap.getPolyanetList().forEach(polyanet -> {
            createPolyanetPort.createPolyanet(polyanet);
        });
        logger.info(requiredSpaceMap.getPolyanetList().size() + " Polyanets created");

        logger.info("Creating Soloons...");
        requiredSpaceMap.getSoloonList().forEach(soloon -> {
            createSoloonPort.createSoloon(soloon);
        });
        logger.info(requiredSpaceMap.getSoloonList().size() + " Soloons created");

        logger.info("Creating Comeths...");
        requiredSpaceMap.getComethList().forEach(cometh -> {
            createComethPort.createCometh(cometh);
        });
        logger.info(requiredSpaceMap.getComethList().size() + " Comeths created");

        logger.info("Finished creating Crossmint logo");
    }
}
