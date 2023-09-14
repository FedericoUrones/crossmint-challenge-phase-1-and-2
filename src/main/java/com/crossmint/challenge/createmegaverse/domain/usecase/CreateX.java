package com.crossmint.challenge.createmegaverse.domain.usecase;

import com.crossmint.challenge.createmegaverse.domain.entities.XCreator;
import com.crossmint.challenge.createmegaverse.domain.ports.api.CreateXCommand;
import com.crossmint.challenge.createmegaverse.domain.ports.spi.CreateXPort;
import com.crossmint.challenge.createmegaverse.infrastructure.ApiClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Data
public class CreateX implements CreateXCommand {

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
        int finalSize = xCreator == null || xCreator.getSize() == null? defaultSize : xCreator.getSize();
        int finalMargin = xCreator == null || xCreator.getMargin() == null? defaultMargin : xCreator.getMargin();
        int size = finalSize;

        // fill matrix with SPACEs
        String[][] result = new String[size][size];
        for(int i = 0; i < size; i++) {
            String[] stringsArray = new String[finalSize];
            Arrays.fill(stringsArray, SPACE);
            result[i] = stringsArray;
        }

        // put POLYANETs in matrix
        for(int i = finalMargin; i < ??; i++) {
            // TODO; ACA PONER LOS POLYANETS EN EL PRINCIPIO Y EN EL FIN
            // algo asi: result[i][i] = POLYANET;

        }
    }
}
