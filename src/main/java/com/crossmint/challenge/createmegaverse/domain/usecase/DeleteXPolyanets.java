package com.crossmint.challenge.createmegaverse.domain.usecase;

import com.crossmint.challenge.createmegaverse.domain.entities.Polyanet;
import com.crossmint.challenge.createmegaverse.domain.entities.SizeAndMargin;
import com.crossmint.challenge.createmegaverse.domain.ports.api.DeleteXPolyanetsCommand;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.DeletePolyanetsPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteXPolyanets implements DeleteXPolyanetsCommand {
    Logger logger = LoggerFactory.getLogger(DeleteXPolyanets.class);

    @Autowired
    private DeletePolyanetsPort deletePolyanetsPort;

    @Value("${size}")
    private Integer defaultSize;

    @Value("${margin}")
    private Integer defaultMargin;

    @Override
    public void execute(SizeAndMargin sizeAndMargin) throws Exception {
        int size = sizeAndMargin == null || sizeAndMargin.getSize() == null || sizeAndMargin.getSize() == 0? defaultSize : sizeAndMargin.getSize();
        int margin = sizeAndMargin == null || sizeAndMargin.getMargin() == null || sizeAndMargin.getMargin() == 0? defaultMargin : sizeAndMargin.getMargin();

        List<Polyanet> polyanets = new ArrayList<>();

        // put POLYANETs in matrix
        for(int startPolyanet = margin; startPolyanet <= size -1 - margin; startPolyanet++) {
            int endPolyanet = size -1 - startPolyanet;
            polyanets.add(new Polyanet(startPolyanet, startPolyanet));
            polyanets.add(new Polyanet(startPolyanet, endPolyanet));
        }

        try {
            polyanets.forEach(polyanet -> {
                deletePolyanetsPort.deletePolyanet(polyanet);
            });
        } catch (Exception e) {
            logger.error("Error happened while deleting polyanets");
            logger.error(e.getLocalizedMessage());
            throw new Exception("Error happened while deleting polyanets");
        }
        logger.info(polyanets.size() + " Polyanets deleted successfully!");
    }
}
