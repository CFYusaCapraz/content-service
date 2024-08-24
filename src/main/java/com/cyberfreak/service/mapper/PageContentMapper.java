package com.cyberfreak.service.mapper;

import com.cyberfreak.service.api.request.CreatePageContentRequest;
import com.cyberfreak.service.dto.PageContentDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PageContentMapper {

    PageContentDto toDTO(CreatePageContentRequest createPageContentRequest);
}
