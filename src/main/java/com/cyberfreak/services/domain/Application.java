package com.cyberfreak.services.domain;

import com.cyberfreak.services.api.context.CycleAvoidingMappingContext;
import com.cyberfreak.services.domain.base.AuditableEntity;
import com.cyberfreak.services.dto.ApplicationDto;
import com.cyberfreak.services.mapper.MapperBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@DynamicUpdate
public class Application extends AuditableEntity<Application, ApplicationDto> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String language;

    @Override
    public ApplicationDto toDto(MapperBase<Application, ApplicationDto> entityMapper) {
        return entityMapper.toDto(this, new CycleAvoidingMappingContext());
    }

    @Override
    public Application fromDto(ApplicationDto referenceDTO, MapperBase<Application, ApplicationDto> entityMapper) {
        return entityMapper.toEntity(referenceDTO, new CycleAvoidingMappingContext());
    }
}