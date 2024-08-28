package com.cyberfreak.services.mapper;

import com.cyberfreak.services.api.request.CreateApplicationRequest;
import com.cyberfreak.services.api.request.UpdateApplicationRequest;
import com.cyberfreak.services.domain.Application;
import com.cyberfreak.services.dto.ApplicationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationMapper extends MapperBase<Application, ApplicationDto> {

    @Override
    Application toEntity(ApplicationDto applicationDto);

    @Override
    ApplicationDto toDto(Application application);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Application partialUpdate(ApplicationDto applicationDto, @MappingTarget Application application);

    ApplicationDto toDto(CreateApplicationRequest createApplicationRequest);

    ApplicationDto toDto(UpdateApplicationRequest updateApplicationRequest);
}