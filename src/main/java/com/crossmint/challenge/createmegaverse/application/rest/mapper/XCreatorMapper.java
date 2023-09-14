package com.crossmint.challenge.createmegaverse.application.rest.mapper;

import com.crossmint.challenge.createmegaverse.application.rest.entities.XCreatorRequest;
import com.crossmint.challenge.createmegaverse.domain.entities.XCreator;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface XCreatorMapper {

    XCreator requestToDomain(XCreatorRequest xCreatorRequest);
}
