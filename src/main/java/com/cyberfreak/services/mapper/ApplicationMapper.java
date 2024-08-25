package com.cyberfreak.services.mapper;

import com.cyberfreak.services.domain.Application;
import com.cyberfreak.services.dto.ApplicationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationMapper {

    Application toEntity(ApplicationDto applicationDto);

    ApplicationDto toDto(Application application);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Application partialUpdate(ApplicationDto applicationDto, @MappingTarget Application application);
}