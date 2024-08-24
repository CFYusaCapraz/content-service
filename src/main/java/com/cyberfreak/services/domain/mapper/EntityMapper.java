package com.cyberfreak.services.domain.mapper;

public interface EntityMapper<Entity, DTO> {

    DTO toDto();

    Entity fromDto(DTO referenceDTO);
}
