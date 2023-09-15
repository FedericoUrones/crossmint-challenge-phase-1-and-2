package com.crossmint.challenge.createmegaverse.domain.usecase;

import com.crossmint.challenge.createmegaverse.domain.entities.Polyanet;
import com.crossmint.challenge.createmegaverse.domain.entities.SizeAndMargin;
import com.crossmint.challenge.createmegaverse.domain.ports.api.CreateXCommand;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.CreatePolyanetPort;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class CreateXPolyanets implements CreateXCommand {

    Logger logger = LoggerFactory.getLogger(CreateXPolyanets.class);

    private static final String SPACE = "SPACE";
    private static final String POLYANET = "POLYANET";

    @Autowired
    private CreatePolyanetPort createPolyanetPort;

    @Value("${size}")
    private Integer defaultSize; // it must be odd and higher than ??

    @Value("${margin}")
    private Integer defaultMargin; // must be higher or equal to 0

    @Override
    public void execute(SizeAndMargin sizeAndMargin) throws Exception {
        int size = sizeAndMargin == null || sizeAndMargin.getSize() == null || sizeAndMargin.getSize() == 0? defaultSize : sizeAndMargin.getSize();
        int margin = sizeAndMargin == null || sizeAndMargin.getMargin() == null || sizeAndMargin.getMargin() == 0? defaultMargin : sizeAndMargin.getMargin();

        List<Polyanet> polyanets = new ArrayList<>();

        // put POLYANETs in matrix
        for(int startPolyanet = margin; startPolyanet <= size -1 - margin; startPolyanet++) {
            int endPolyanet = size -1 - startPolyanet;
            polyanets.add(new Polyanet(startPolyanet, startPolyanet));
            if(startPolyanet != endPolyanet) {
                polyanets.add(new Polyanet(startPolyanet, endPolyanet));
            }
        }

        try {
            polyanets.forEach(polyanet -> createPolyanetPort.createPolyanet(polyanet));
        } catch (Exception e) {
            logger.error("Error happened while creating polyanets");
            logger.error(e.getLocalizedMessage());
            throw new Exception("Error happened while creating polyanets");
        }
        logger.info(polyanets.size() + " Polyanets created successfully!");
    }
}
