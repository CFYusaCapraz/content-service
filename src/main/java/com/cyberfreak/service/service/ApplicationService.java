package com.cyberfreak.service.service;

import com.cyberfreak.service.dto.ApplicationDto;
import org.mapstruct.Named;

public interface ApplicationService {

    @Named("mapApplicationIdToApplicationDto")
    ApplicationDto getApplication(Long id);
}
