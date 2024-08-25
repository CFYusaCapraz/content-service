package com.cyberfreak.services.mapper;

import com.cyberfreak.services.domain.Menu;
import com.cyberfreak.services.dto.MenuDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ApplicationMapper.class, MenuItemMapper.class})
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    @AfterMapping
    default void linkMenuItems(@MappingTarget @NotNull Menu menu) {
        menu.getMenuItems().forEach(menuItem -> menuItem.setMenu(menu));
    }

    Menu toEntity(MenuDto menuDto);

    MenuDto toDto(Menu menu);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Menu partialUpdate(MenuDto menuDto, @MappingTarget Menu menu);
}