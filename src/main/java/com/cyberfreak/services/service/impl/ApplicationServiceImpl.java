package com.cyberfreak.services.service.impl;

import com.cyberfreak.services.dto.ApplicationDto;
import com.cyberfreak.services.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {
    @Override
    public ApplicationDto getApplication(@NotNull Long id) {
        return null;
    }

    @Override
    public ApplicationDto getApplication(@Nullable Long id, @NotNull Long parentApplicationId) {
        return null;
    }
}
