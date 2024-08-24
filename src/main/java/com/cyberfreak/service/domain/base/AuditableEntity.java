package com.cyberfreak.service.domain.base;

import com.cyberfreak.service.domain.mapper.EntityMapper;
import com.cyberfreak.service.dto.base.AuditableDto;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<Entity extends AuditableEntity<Entity, DTO>, DTO extends AuditableDto> extends BaseEntity<Entity, DTO> implements EntityMapper<Entity, DTO> {

    @Column(name = "creation_time", nullable = false, updatable = false)
    protected LocalDateTime creationTime;

    @Column(name = "modification_time")
    protected LocalDateTime modificationTime;

    @Column(name = "deletion_time", updatable = false)
    protected LocalDateTime deletionTime;

    @Column(name = "created_by", nullable = false, updatable = false)
    protected String createdBy;

    @Column(name = "modified_by")
    protected String modifiedBy;

    @Column(name = "deleted_by", updatable = false)
    protected String deletedBy;

    public abstract DTO toDto();

    public abstract Entity fromDto(DTO referenceDTO);
}