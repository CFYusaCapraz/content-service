package com.cyberfreak.services.mapper;

import com.cyberfreak.services.api.request.pagecontent.PageContentContentItemRequest;
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

    @Mapping(source = "key", target = "resourceMap.resourceKey")
    @Mapping(source = "value", target = "resourceMap.resourceValue")
    ContentItemDto toDto(PageContentContentItemRequest pageContentContentItemRequest);
}
