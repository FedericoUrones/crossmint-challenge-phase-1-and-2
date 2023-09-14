package com.crossmint.challenge.createmegaverse.mapper;

import com.crossmint.challenge.createmegaverse.domain.entities.*;
import com.crossmint.challenge.createmegaverse.infrastructure.entities.SpaceMapGoalResponse;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SpaceMapMapper {

    private static final String SPACE = "SPACE";
    private static final String POLYANET = "POLYANET";

    public SpaceMap spaceMapGoalResponseToSpaceMap(SpaceMapGoalResponse spaceMapGoalResponse) {
        List<SpaceMapRow> rowsResult = new ArrayList<>();
        spaceMapGoalResponse.getGoal().forEach(columns -> {

            List<SpaceElement> columnsResult = new ArrayList<>();
            columns.forEach(column -> {
                switch (column) {
                    case SPACE -> columnsResult.add(new Space());
                    case POLYANET -> columnsResult.add(new Polyanet());
                }
            });

            rowsResult.add(new SpaceMapRow(columnsResult));
        });
        return new SpaceMap(rowsResult);
    }
}
