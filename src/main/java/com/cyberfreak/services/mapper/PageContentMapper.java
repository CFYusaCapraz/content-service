package com.cyberfreak.services.mapper;

import com.cyberfreak.services.api.request.CreatePageContentRequest;
import com.cyberfreak.services.api.request.CreatePageContentWithExistingItemsRequest;
import com.cyberfreak.services.api.request.CreatePageContentWithItemsRequest;
import com.cyberfreak.services.domain.PageContent;
import com.cyberfreak.services.dto.PageContentDto;
import com.cyberfreak.services.service.ApplicationService;
import com.cyberfreak.services.service.ContentItemService;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ApplicationService.class, ContentItemMapper.class, ContentItemService.class})
public interface PageContentMapper {

    PageContentMapper INSTANCE = Mappers.getMapper(PageContentMapper.class);

    @AfterMapping
    default void linkContentItems(@MappingTarget @NotNull PageContent pageContent) {
        pageContent.getContentItems().forEach(contentItem -> contentItem.setPage(pageContent));
    }

    @AfterMapping
    default void linkContentItems(@MappingTarget @NotNull PageContentDto pageContentDto) {
        pageContentDto.getContentItems().forEach(contentItem -> contentItem.setPage(pageContentDto));
    }

    PageContent toEntity(PageContentDto pageContentDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PageContent partialUpdate(PageContentDto pageContentDto, @MappingTarget PageContent pageContent);

    PageContentDto toDto(PageContent pageContent);

    @Mapping(source = "applicationId", target = "application", qualifiedByName = "mapApplicationIdToApplicationDto")
    PageContentDto toDto(CreatePageContentRequest createPageContentRequest);

    @Mapping(source = "applicationId", target = "application", qualifiedByName = "mapApplicationIdToApplicationDto")
    @Mapping(source = "contentItems", target = "contentItems", qualifiedByName = "mapCreateContentItemRequestToContentItemDtoWithContext")
    PageContentDto toDto(CreatePageContentWithItemsRequest createPageContentWithItemsRequest, @Context Long pageContentApplicationId);

    @Mapping(source = "applicationId", target = "application", qualifiedByName = "mapApplicationIdToApplicationDto")
    @Mapping(source = "contentItems", target = "contentItems", qualifiedByName = "mapContentItemIdToContentItemDto")
    PageContentDto toDto(CreatePageContentWithExistingItemsRequest createPageContentWithExistingItemsRequest);
}
