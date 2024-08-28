package com.cyberfreak.services.mapper;

import com.cyberfreak.services.api.context.CycleAvoidingMappingContext;
import com.cyberfreak.services.domain.base.BaseEntity;
import com.cyberfreak.services.dto.base.BaseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface MapperBase<Entity extends BaseEntity<Entity, DTO>, DTO extends BaseDto> {

    Entity toEntity(DTO baseDto, @Context CycleAvoidingMappingContext context);

    DTO toDto(Entity baseEntity, @Context CycleAvoidingMappingContext context);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Entity partialUpdate(DTO baseDto, @MappingTarget Entity baseEntity, @Context CycleAvoidingMappingContext context);

}
