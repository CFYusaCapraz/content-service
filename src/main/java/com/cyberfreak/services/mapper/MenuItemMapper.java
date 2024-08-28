package com.cyberfreak.services.mapper;

import com.cyberfreak.services.domain.MenuItem;
import com.cyberfreak.services.dto.MenuItemDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "springlazy",
        uses = {ApplicationMapper.class, MenuMapper.class})
public interface MenuItemMapper extends MapperBase<MenuItem, MenuItemDto> {

    @AfterMapping
    default void linkChildren(@MappingTarget @NotNull MenuItem menuItem) {
        Optional.ofNullable(menuItem.getChildren()).ifPresent(menuItems ->
                menuItems.forEach(child -> child.setParent(menuItem)));
    }

    @Override
    MenuItem toEntity(MenuItemDto menuItemDto);

    @Override
    MenuItemDto toDto(MenuItem menuItem);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MenuItem partialUpdate(MenuItemDto menuItemDto, @MappingTarget MenuItem menuItem);
}