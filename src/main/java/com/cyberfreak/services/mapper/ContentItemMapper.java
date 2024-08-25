package com.cyberfreak.services.mapper;

import com.cyberfreak.services.api.request.CreateContentItemRequest;
import com.cyberfreak.services.domain.ContentItem;
import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.service.ApplicationService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ApplicationService.class})
public interface ContentItemMapper {

    ContentItemMapper INSTANCE = Mappers.getMapper(ContentItemMapper.class);

    ContentItem toEntity(ContentItemDto contentItemDto);

    ContentItemDto toDto(ContentItem contentItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContentItem partialUpdate(ContentItemDto contentItemDto, @MappingTarget ContentItem contentItem);

    @Named("mapCreateContentItemRequestToContentItemDtoWithContext")
    @Mapping(source = "key", target = "resourceMap.resourceKey")
    @Mapping(source = "value", target = "resourceMap.resourceValue")
    @Mapping(source = "applicationId", target = "application", qualifiedByName = "mapApplicationIdToApplicationDtoWithContext")
    ContentItemDto toDto(CreateContentItemRequest createContentItemRequest, @Context Long applicationId);
}
