package com.cyberfreak.services.mapper;

import com.cyberfreak.services.api.context.CycleAvoidingMappingContext;
import com.cyberfreak.services.domain.Menu;
import com.cyberfreak.services.dto.MenuDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ApplicationMapper.class, MenuItemMapper.class})
public interface MenuMapper extends MapperBase<Menu, MenuDto> {

    @AfterMapping
    default void linkMenuItems(@MappingTarget @NotNull Menu menu) {
        Optional.ofNullable(menu.getMenuItems()).ifPresent(menuItems ->
                menuItems.forEach(menuItem -> menuItem.setMenu(menu)));
    }

    @Override
    Menu toEntity(MenuDto menuDto, @Context CycleAvoidingMappingContext context);

    @Override
    MenuDto toDto(Menu menu, @Context CycleAvoidingMappingContext context);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Menu partialUpdate(MenuDto menuDto, @MappingTarget Menu menu, @Context CycleAvoidingMappingContext context);
}