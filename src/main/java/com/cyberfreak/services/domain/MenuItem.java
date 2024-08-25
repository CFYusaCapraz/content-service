package com.cyberfreak.services.domain;

import com.cyberfreak.services.domain.base.AuditableEntity;
import com.cyberfreak.services.domain.embeddable.ResourceMap;
import com.cyberfreak.services.dto.MenuItemDto;
import com.cyberfreak.services.mapper.MenuItemMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "menu_item")
@DynamicUpdate
public class MenuItem extends AuditableEntity<MenuItem, MenuItemDto> {

    @Embedded
    private ResourceMap resourceMap;

    @ManyToOne(optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Column(nullable = false)
    private Boolean active = Boolean.TRUE;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private MenuItem parent;

    @OneToMany(mappedBy = "parent")
    @OrderBy("orderIndex ASC")
    private List<MenuItem> children;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Override
    public MenuItemDto toDto() {
        return MenuItemMapper.INSTANCE.toDto(this);
    }

    @Override
    public MenuItem fromDto(MenuItemDto referenceDTO) {
        return MenuItemMapper.INSTANCE.toEntity(referenceDTO);
    }
}