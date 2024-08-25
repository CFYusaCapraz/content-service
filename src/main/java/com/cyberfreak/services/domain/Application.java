package com.cyberfreak.services.domain;

import com.cyberfreak.services.domain.base.AuditableEntity;
import com.cyberfreak.services.dto.ApplicationDto;
import com.cyberfreak.services.mapper.ApplicationMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Application extends AuditableEntity<Application, ApplicationDto> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String language;

    @Override
    public ApplicationDto toDto() {
        return ApplicationMapper.INSTANCE.toDto(this);
    }

    @Override
    public Application fromDto(ApplicationDto referenceDTO) {
        return ApplicationMapper.INSTANCE.toEntity(referenceDTO);
    }
}