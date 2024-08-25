package com.cyberfreak.services.mapper;

import com.cyberfreak.services.domain.MenuItem;
import com.cyberfreak.services.dto.MenuItemDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuItemMapper {

    MenuItemMapper INSTANCE = Mappers.getMapper(MenuItemMapper.class);

    @AfterMapping
    default void linkChildren(@MappingTarget @NotNull MenuItem menuItem) {
        menuItem.getChildren().forEach(child -> child.setParent(menuItem));
    }

    MenuItem toEntity(MenuItemDto menuItemDto);

    MenuItemDto toDto(MenuItem menuItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MenuItem partialUpdate(MenuItemDto menuItemDto, @MappingTarget MenuItem menuItem);
}