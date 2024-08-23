package com.cyberfreak.service.domain.base;

import com.cyberfreak.service.domain.listeners.BaseEntityListener;
import com.cyberfreak.service.dto.base.BaseEntityDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = Boolean.FALSE;

    public abstract <T extends BaseEntityDto> T toDTO();

    public abstract <T extends BaseEntityDto, V extends BaseEntity> V fromDTO(T referenceDTO);
}