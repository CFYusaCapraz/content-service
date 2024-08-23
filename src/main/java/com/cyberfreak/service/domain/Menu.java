package com.cyberfreak.service.domain;

import com.cyberfreak.service.domain.base.AuditableEntity;
import com.cyberfreak.service.dto.MenuDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Menu extends AuditableEntity<Menu, MenuDto> {

    @ManyToOne(optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @OneToMany(mappedBy = "menu")
    @OrderBy("orderIndex ASC")
    private List<MenuItem> menuItems;

    @Override
    public MenuDto toDTO() {
        return null;
    }

    @Override
    public Menu fromDTO(MenuDto referenceDTO) {
        return null;
    }
}