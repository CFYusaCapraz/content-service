package com.cyberfreak.services.mapper;

import com.cyberfreak.services.api.context.CycleAvoidingMappingContext;
import com.cyberfreak.services.api.request.application.CreateApplicationRequest;
import com.cyberfreak.services.api.request.application.UpdateApplicationRequest;
import com.cyberfreak.services.api.response.light.ApplicationLightResponse;
import com.cyberfreak.services.domain.Application;
import com.cyberfreak.services.dto.ApplicationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationMapper extends MapperBase<Application, ApplicationDto> {

    @Override
    Application toEntity(ApplicationDto applicationDto, @Context CycleAvoidingMappingContext context);

    @Override
    ApplicationDto toDto(Application application, @Context CycleAvoidingMappingContext context);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Application partialUpdate(ApplicationDto applicationDto, @MappingTarget Application application, @Context CycleAvoidingMappingContext context);

    ApplicationDto toDto(CreateApplicationRequest createApplicationRequest);

    ApplicationDto toDto(UpdateApplicationRequest updateApplicationRequest);

    ApplicationLightResponse toResponse(ApplicationDto applicationDto);
}