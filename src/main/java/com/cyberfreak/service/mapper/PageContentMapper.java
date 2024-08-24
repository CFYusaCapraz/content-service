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

    PageContent toEntity(PageContentDto pageContentDto);

    @AfterMapping
    default void linkContentItems(@MappingTarget @NotNull PageContent pageContent) {
        pageContent.getContentItems().forEach(contentItem -> contentItem.setPage(pageContent));
    }

    PageContentDto toDto(PageContent pageContent);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PageContent partialUpdate(PageContentDto pageContentDto, @MappingTarget PageContent pageContent);


    @Mapping(source = "pageName", target = "pageName")
    @Mapping(source = "applicationId", target = "application", qualifiedByName = "mapApplicationIdToApplicationDto")
    PageContentDto toDto(CreatePageContentRequest createPageContentRequest);
}
