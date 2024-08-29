package com.cyberfreak.services.mapper;

import com.cyberfreak.services.api.context.CycleAvoidingMappingContext;
import com.cyberfreak.services.api.request.pagecontent.PageContentContentItemRequest;
import com.cyberfreak.services.api.response.light.ContentItemLightResponse;
import com.cyberfreak.services.domain.ContentItem;
import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.service.ApplicationService;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "springlazy",
        uses = {ApplicationService.class, ApplicationMapper.class, PageContentMapper.class})
public interface ContentItemMapper extends MapperBase<ContentItem, ContentItemDto> {

    @Override
    ContentItem toEntity(ContentItemDto contentItemDto, @Context CycleAvoidingMappingContext context);

    @Override
    ContentItemDto toDto(ContentItem contentItem, @Context CycleAvoidingMappingContext context);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContentItem partialUpdate(ContentItemDto contentItemDto, @MappingTarget ContentItem contentItem, @Context CycleAvoidingMappingContext context);

    @Mapping(source = "key", target = "resourceMap.resourceKey")
    @Mapping(source = "value", target = "resourceMap.resourceValue")
    ContentItemDto toDto(PageContentContentItemRequest pageContentContentItemRequest);

    @Mapping(target = "application", source = "application.id")
    ContentItemLightResponse toResponse(ContentItemDto contentItemDto);
}
