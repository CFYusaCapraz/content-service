package com.cyberfreak.services.domain;

import com.cyberfreak.services.domain.base.AuditableEntity;
import com.cyberfreak.services.dto.MenuDto;
import com.cyberfreak.services.mapper.MapperBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@Setter
@Entity
@DynamicUpdate
public class Menu extends AuditableEntity<Menu, MenuDto> {

    @ManyToOne(optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @OneToMany(mappedBy = "menu")
    @OrderBy("orderIndex ASC")
    private List<MenuItem> menuItems;

    @Override
    public MenuDto toDto(MapperBase<Menu, MenuDto> entityMapper) {
        return entityMapper.toDto(this);
    }

    @Override
    public Menu fromDto(MenuDto referenceDTO, MapperBase<Menu, MenuDto> entityMapper) {
        return entityMapper.toEntity(referenceDTO);
    }
}