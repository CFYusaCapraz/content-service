package com.cyberfreak.services.domain.base;

import com.cyberfreak.services.domain.listeners.BaseEntityListener;
import com.cyberfreak.services.domain.mapper.EntityMapper;
import com.cyberfreak.services.dto.base.BaseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
@SoftDelete(columnName = "is_deleted", strategy = SoftDeleteType.DELETED)
public abstract class BaseEntity<Entity extends BaseEntity<Entity, DTO>, DTO extends BaseDto> implements EntityMapper<Entity, DTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Version
    @Column(name = "version", nullable = false)
    protected Integer version;

    @Column(name = "is_deleted", nullable = false, insertable = false, updatable = false)
    protected Boolean isDeleted = Boolean.FALSE;

    @Override
    public abstract DTO toDto();

    @Override
    public abstract Entity fromDto(DTO referenceDTO);
}