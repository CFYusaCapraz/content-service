package com.cyberfreak.service.mapper;

import com.cyberfreak.service.api.request.CreateContentItemRequest;
import com.cyberfreak.service.dto.ContentItemDto;
import com.cyberfreak.service.service.ApplicationService;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ApplicationService.class})
public interface ContentItemMapper {

    @Mapping(source = "key", target = "resourceMap.resourceKey")
    @Mapping(source = "value", target = "resourceMap.resourceValue")
    @Mapping(source = "applicationId", target = "application", qualifiedByName = "mapApplicationIdToApplicationDtoWithContext")
    ContentItemDto toDto(CreateContentItemRequest createContentItemRequest, @Context Long applicationId);
}
