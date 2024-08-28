package com.cyberfreak.services.mapper;

import com.cyberfreak.services.api.context.CycleAvoidingMappingContext;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentRequest;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentWithExistingItemsRequest;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentWithItemsRequest;
import com.cyberfreak.services.domain.PageContent;
import com.cyberfreak.services.dto.PageContentDto;
import com.cyberfreak.services.service.ApplicationService;
import com.cyberfreak.services.service.ContentItemService;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ApplicationService.class, ContentItemService.class,
                ApplicationMapper.class, ContentItemMapper.class})
public interface PageContentMapper extends MapperBase<PageContent, PageContentDto> {

    @AfterMapping
    default void linkContentItems(PageContentDto pageContentDto, @MappingTarget @NotNull PageContent pageContent) {
        Optional.ofNullable(pageContent.getContentItems()).ifPresent(contentItems ->
                contentItems.forEach(contentItem -> contentItem.setPage(pageContent)));
    }

    @AfterMapping
    default void linkContentItems(PageContent pageContent, @MappingTarget @NotNull PageContentDto pageContentDto) {
        Optional.ofNullable(pageContentDto.getContentItems()).ifPresent(contentItemDtoSet ->
                contentItemDtoSet.forEach(contentItemDto -> contentItemDto.setPage(pageContentDto)));
    }

    @AfterMapping
    default void linkContentItems(CreatePageContentWithItemsRequest createPageContentWithItemsRequest, @MappingTarget @NotNull PageContentDto pageContentDto) {
        Optional.ofNullable(pageContentDto.getContentItems()).ifPresent(contentItemDtoSet ->
                contentItemDtoSet.forEach(contentItemDto -> {
                    contentItemDto.setPage(pageContentDto);
                    contentItemDto.setApplication(pageContentDto.getApplication());
                }));
    }

    @AfterMapping
    default void linkContentItems(CreatePageContentWithExistingItemsRequest createPageContentWithExistingItemsRequest, @MappingTarget @NotNull PageContentDto pageContentDto) {
        Optional.ofNullable(pageContentDto.getContentItems()).ifPresent(contentItemDtoSet ->
                contentItemDtoSet.forEach(contentItemDto -> contentItemDto.setPage(pageContentDto)));
    }

    @Override
    PageContent toEntity(PageContentDto pageContentDto, @Context CycleAvoidingMappingContext context);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PageContent partialUpdate(PageContentDto pageContentDto, @MappingTarget PageContent pageContent, @Context CycleAvoidingMappingContext context);

    @Override
    PageContentDto toDto(PageContent pageContent, @Context CycleAvoidingMappingContext context);

    @Mapping(source = "applicationId", target = "application", qualifiedByName = "mapApplicationIdToApplicationDto")
    PageContentDto toDto(CreatePageContentRequest createPageContentRequest);

    @Mapping(source = "applicationId", target = "application", qualifiedByName = "mapApplicationIdToApplicationDto")
    PageContentDto toDto(CreatePageContentWithItemsRequest createPageContentWithItemsRequest);

    @Mapping(source = "applicationId", target = "application", qualifiedByName = "mapApplicationIdToApplicationDto")
    @Mapping(source = "contentItems", target = "contentItems", qualifiedByName = "mapContentItemIdToContentItemDto")
    PageContentDto toDto(CreatePageContentWithExistingItemsRequest createPageContentWithExistingItemsRequest);
}
