package com.cyberfreak.service.domain;

import com.cyberfreak.service.domain.base.AuditableEntity;
import com.cyberfreak.service.dto.ApplicationDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
        return null;
    }

    @Override
    public Application fromDto(ApplicationDto referenceDTO) {
        return null;
    }
}