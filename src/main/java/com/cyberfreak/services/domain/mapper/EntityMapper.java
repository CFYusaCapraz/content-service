package com.cyberfreak.services.domain.mapper;

import com.cyberfreak.services.domain.base.BaseEntity;
import com.cyberfreak.services.dto.base.BaseDto;
import com.cyberfreak.services.mapper.MapperBase;

public interface EntityMapper<Entity extends BaseEntity<Entity, DTO>, DTO extends BaseDto> {

    DTO toDto(MapperBase<Entity, DTO> entityMapper);

    Entity fromDto(DTO referenceDTO, MapperBase<Entity, DTO> entityMapper);
}
