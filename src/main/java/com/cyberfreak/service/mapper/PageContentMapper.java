package com.cyberfreak.service.mapper;

import com.cyberfreak.service.api.request.CreatePageContentRequest;
import com.cyberfreak.service.domain.PageContent;
import com.cyberfreak.service.dto.PageContentDto;
import com.cyberfreak.service.service.ApplicationService;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ApplicationService.class})
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
}
