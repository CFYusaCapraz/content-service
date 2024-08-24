package com.cyberfreak.service.domain.base;

import com.cyberfreak.service.domain.listeners.BaseEntityListener;
import com.cyberfreak.service.domain.mapper.EntityMapper;
import com.cyberfreak.service.dto.base.BaseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public abstract class BaseEntity<Entity extends BaseEntity<Entity, DTO>, DTO extends BaseDto> implements EntityMapper<Entity, DTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = Boolean.FALSE;

    @Override
    public abstract DTO toDto();

    @Override
    public abstract Entity fromDto(DTO referenceDTO);
}