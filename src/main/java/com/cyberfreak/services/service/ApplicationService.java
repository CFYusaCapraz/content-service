package com.cyberfreak.services.service;

import com.cyberfreak.services.api.request.application.CreateApplicationRequest;
import com.cyberfreak.services.api.request.application.UpdateApplicationRequest;
import com.cyberfreak.services.dto.ApplicationDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Named;

import java.util.List;

public interface ApplicationService {

    @Named("mapApplicationIdToApplicationDto")
    ApplicationDto getApplication(@NotNull Long id);

    List<ApplicationDto> getApplications();

    List<ApplicationDto> getApplicationsByNameAndLanguage(String name, String language);

    ApplicationDto createApplication(CreateApplicationRequest createApplicationRequest);

    ApplicationDto updateApplication(Long id, UpdateApplicationRequest updateApplicationRequest);

    void deleteApplication(Long id);
}
