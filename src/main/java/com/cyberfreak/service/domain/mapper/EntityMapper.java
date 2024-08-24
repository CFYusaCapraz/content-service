package com.cyberfreak.service.domain.mapper;

public interface EntityMapper<Entity, DTO> {

    DTO toDto();

    Entity fromDto(DTO referenceDTO);
}
