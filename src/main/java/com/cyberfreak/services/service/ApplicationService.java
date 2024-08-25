package com.cyberfreak.services.service;

import com.cyberfreak.services.dto.ApplicationDto;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mapstruct.Context;
import org.mapstruct.Named;

import java.util.List;

public interface ApplicationService {

    @Named("mapApplicationIdToApplicationDto")
    ApplicationDto getApplication(@NotNull Long id);

    @Named("mapApplicationIdToApplicationDtoWithContext")
    ApplicationDto getApplication(@Nullable Long id, @NotNull @Context Long parentApplicationId);

    List<ApplicationDto> getApplications();

    List<ApplicationDto> getApplicationsByNameAndLanguage(String name, String language);
}
