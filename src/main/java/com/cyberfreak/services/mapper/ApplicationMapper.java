package com.cyberfreak.services.mapper;

import com.cyberfreak.services.api.request.CreateApplicationRequest;
import com.cyberfreak.services.api.request.UpdateApplicationRequest;
import com.cyberfreak.services.domain.Application;
import com.cyberfreak.services.dto.ApplicationDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationMapper {

    ApplicationMapper INSTANCE = Mappers.getMapper(ApplicationMapper.class);

    Application toEntity(ApplicationDto applicationDto);

    ApplicationDto toDto(Application application);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Application partialUpdate(ApplicationDto applicationDto, @MappingTarget Application application);

    ApplicationDto toDto(CreateApplicationRequest createApplicationRequest);

    ApplicationDto toDto(UpdateApplicationRequest updateApplicationRequest);
}