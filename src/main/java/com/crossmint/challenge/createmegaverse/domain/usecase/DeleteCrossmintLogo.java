package com.crossmint.challenge.createmegaverse.domain.usecase;

import com.crossmint.challenge.createmegaverse.domain.entities.SpaceMap;
import com.crossmint.challenge.createmegaverse.domain.ports.api.CreateCrossmintLogoCommand;
import com.crossmint.challenge.createmegaverse.domain.ports.api.DeleteCrossmintLogoCommand;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCrossmintLogo implements DeleteCrossmintLogoCommand {

    Logger logger = LoggerFactory.getLogger(DeleteCrossmintLogo.class);

    @Autowired
    private GetGoalMapPort getGoalMapPort;

    @Autowired
    private DeletePolyanetsPort deletePolyanetPort;

    @Autowired
    private DeleteSoloonPort deleteSoloonPort;

    @Autowired
    private DeleteComethPort deleteComethPort;


    @Override
    public void execute() throws Exception {

        SpaceMap requiredSpaceMap = getGoalMapPort.getGoalMap();
        logger.info("Got required goal map");

        logger.info("Deleting Polyanets...");
        requiredSpaceMap.getPolyanetList().forEach(polyanet -> {
            deletePolyanetPort.deletePolyanet(polyanet);
        });
        logger.info(requiredSpaceMap.getPolyanetList().size() + " Polyanets deleted");

        logger.info("Deleting Soloons...");
        requiredSpaceMap.getSoloonList().forEach(soloon -> {
            deleteSoloonPort.deleteSoloon(soloon);
        });
        logger.info(requiredSpaceMap.getSoloonList().size() + " Soloons deleted");

        logger.info("Deleting Comeths...");
        requiredSpaceMap.getComethList().forEach(cometh -> {
            deleteComethPort.deleteCometh(cometh);
        });
        logger.info(requiredSpaceMap.getComethList().size() + " Comeths deleted");

        logger.info("Finished deleting crossming logo");
    }
}
