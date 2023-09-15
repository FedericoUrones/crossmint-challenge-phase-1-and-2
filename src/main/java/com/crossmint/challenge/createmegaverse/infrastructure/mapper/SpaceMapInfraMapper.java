package com.crossmint.challenge.createmegaverse.infrastructure.mapper;

import com.crossmint.challenge.createmegaverse.domain.entities.*;
import com.crossmint.challenge.createmegaverse.infrastructure.entities.SpaceMapGoalResponse;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public abstract class SpaceMapInfraMapper {

    public SpaceMap spaceMapGoalResponseToSpaceMap(SpaceMapGoalResponse spaceMapGoalResponse) {
        if (spaceMapGoalResponse == null) {
            return null;
        }
        List<Polyanet> polyanetList = new ArrayList<>();
        List<Soloon> soloonList = new ArrayList<>();
        List<Cometh> comethList = new ArrayList<>();
        for(int row = 0; row < spaceMapGoalResponse.getGoal().size(); row++) {
            List<String> currentRow = spaceMapGoalResponse.getGoal().get(row);

            for(int column = 0; column < currentRow.size(); column++) {
                String[] splits = currentRow.get(column).split("_");
                EntitiesDirections direction = null;
                EntitiesColors color = null;
                EntitiesNames spaceElement = null;

                if (splits.length == 1 ) {
                    spaceElement = EntitiesNames.fromString(splits[0]);
                    if (spaceElement == EntitiesNames.SPACE) {
                        continue;
                    }
                } else {
                    spaceElement = EntitiesNames.fromString(splits[1]);
                    switch (Objects.requireNonNull(spaceElement)) {
                        case COMETH -> {
                            direction = EntitiesDirections.fromString(splits[0]);
                        }
                        case SOLOON -> {
                            color = EntitiesColors.fromString(splits[0]);
                        }
                    }
                }

                switch (Objects.requireNonNull(spaceElement)) {
                    case POLYANET -> polyanetList.add(new Polyanet(row, column));
                    case COMETH -> comethList.add(new Cometh(row, column, direction));
                    case SOLOON -> soloonList.add(new Soloon(row, column, color));
                }
            }

        }
        return new SpaceMap(polyanetList, soloonList, comethList);
    }
}
