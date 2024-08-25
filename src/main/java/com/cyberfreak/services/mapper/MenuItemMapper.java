package com.cyberfreak.services.mapper;

import com.cyberfreak.services.domain.MenuItem;
import com.cyberfreak.services.dto.MenuItemDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuItemMapper {

    @AfterMapping
    default void linkChildren(@MappingTarget @NotNull MenuItem menuItem) {
        menuItem.getChildren().forEach(child -> child.setParent(menuItem));
    }

    MenuItem toEntity(MenuItemDto menuItemDto);

    MenuItemDto toDto(MenuItem menuItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MenuItem partialUpdate(MenuItemDto menuItemDto, @MappingTarget MenuItem menuItem);
}