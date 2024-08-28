package com.cyberfreak.services.mapper;

import com.cyberfreak.services.api.request.CreateContentItemRequest;
import com.cyberfreak.services.domain.ContentItem;
import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.service.ApplicationService;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "springlazy",
        uses = {ApplicationService.class, ApplicationMapper.class, PageContentMapper.class})
public interface ContentItemMapper extends MapperBase<ContentItem, ContentItemDto> {

    @Override
    ContentItem toEntity(ContentItemDto contentItemDto);

    @Override
    ContentItemDto toDto(ContentItem contentItem);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContentItem partialUpdate(ContentItemDto contentItemDto, @MappingTarget ContentItem contentItem);

    @Named("mapCreateContentItemRequestToContentItemDtoWithContext")
    @Mapping(source = "key", target = "resourceMap.resourceKey")
    @Mapping(source = "value", target = "resourceMap.resourceValue")
    @Mapping(source = "applicationId", target = "application", qualifiedByName = "mapApplicationIdToApplicationDtoWithContext")
    ContentItemDto toDto(CreateContentItemRequest createContentItemRequest, @Context Long applicationId);
}
