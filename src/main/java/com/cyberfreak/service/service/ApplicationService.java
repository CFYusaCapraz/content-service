package com.cyberfreak.service.service;

import com.cyberfreak.service.dto.ApplicationDto;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mapstruct.Context;
import org.mapstruct.Named;

public interface ApplicationService {

    @Named("mapApplicationIdToApplicationDto")
    ApplicationDto getApplication(@NotNull Long id);

    @Named("mapApplicationIdToApplicationDtoWithContext")
    ApplicationDto getApplication(@Nullable Long id, @NotNull @Context Long parentApplicationId);
}
