package com.crossmint.challenge.createmegaverse.application.rest.mapper;

import com.crossmint.challenge.createmegaverse.application.rest.entities.SpaceMapResponse;
import com.crossmint.challenge.createmegaverse.domain.entities.SpaceMap;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpaceMapMapper {

    SpaceMapResponse domainToResponse(SpaceMap spaceMap);
}
