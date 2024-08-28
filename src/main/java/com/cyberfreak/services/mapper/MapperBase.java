package com.cyberfreak.services.mapper;

import com.cyberfreak.services.domain.base.BaseEntity;
import com.cyberfreak.services.dto.base.BaseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface MapperBase<Entity extends BaseEntity<Entity, DTO>, DTO extends BaseDto> {

    Entity toEntity(DTO baseDto);

    DTO toDto(Entity baseEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Entity partialUpdate(DTO baseDto, @MappingTarget Entity baseEntity);

}
