package com.crossmint.challenge.createmegaverse.application.rest.mapper;

import com.crossmint.challenge.createmegaverse.application.rest.entities.SizeAndMarginRequest;
import com.crossmint.challenge.createmegaverse.domain.entities.SizeAndMargin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SizeAndMarginMapper {

    SizeAndMargin requestToDomain(SizeAndMarginRequest sizeAndMarginRequest);
}
