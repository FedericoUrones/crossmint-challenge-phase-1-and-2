package com.crossmint.challenge.createmegaverse.domain.usecase;

import com.crossmint.challenge.createmegaverse.domain.entities.Polyanet;
import com.crossmint.challenge.createmegaverse.domain.entities.XCreator;
import com.crossmint.challenge.createmegaverse.domain.ports.api.CreateXCommand;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.CreateXPort;
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
public class CreateX implements CreateXCommand {

    Logger logger = LoggerFactory.getLogger(CreateX.class);

    private static final String SPACE = "SPACE";
    private static final String POLYANET = "POLYANET";

    @Autowired
    private CreateXPort createXPort;

    @Value("${size}")
    private Integer defaultSize; // it must be odd and higher than ??

    @Value("${margin}")
    private Integer defaultMargin; // must be higher or equal to 0

    @Override
    public void execute(XCreator xCreator) {
        int size = xCreator == null || xCreator.getSize() == null? defaultSize : xCreator.getSize();
        int margin = xCreator == null || xCreator.getMargin() == null? defaultMargin : xCreator.getMargin();

        List<Polyanet> polyanets = new ArrayList<>();

        // put POLYANETs in matrix
        for(int i = margin; i < size -1 - margin; i++) {
            int startPolyanet = i + margin;
            int endPolyanet = size -1 - i - margin;
            polyanets.add(new Polyanet(startPolyanet, startPolyanet));
            polyanets.add(new Polyanet(endPolyanet, endPolyanet));
        }

        try {
            polyanets.forEach(polyanet -> createXPort.createX(polyanet));
        } catch (Exception e) {
            logger.error("Error happened while creating polyanets");
            logger.error(e.getLocalizedMessage());
        }
        logger.info(polyanets.size() + " Polyanets created successfully!");
    }
}
