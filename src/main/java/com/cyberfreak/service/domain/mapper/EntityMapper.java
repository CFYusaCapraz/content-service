package com.cyberfreak.service.domain.mapper;

public interface EntityMapper<Entity, DTO> {

    DTO toDTO();

    Entity fromDTO(DTO referenceDTO);
}
