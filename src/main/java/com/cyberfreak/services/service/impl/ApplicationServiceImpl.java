package com.cyberfreak.services.service.impl;

import com.cyberfreak.services.api.request.CreateApplicationRequest;
import com.cyberfreak.services.api.request.UpdateApplicationRequest;
import com.cyberfreak.services.domain.Application;
import com.cyberfreak.services.dto.ApplicationDto;
import com.cyberfreak.services.mapper.ApplicationMapper;
import com.cyberfreak.services.repository.ApplicationRepository;
import com.cyberfreak.services.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;

    @Override
    public ApplicationDto getApplication(@NotNull Long id) {
        return applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found")).toDto(applicationMapper);
    }

    @Override
    public ApplicationDto getApplication(@Nullable Long id, @NotNull Long parentApplicationId) {
        return getApplication(Objects.requireNonNullElse(id, parentApplicationId));
    }

    @Override
    public List<ApplicationDto> getApplications() {
        return applicationRepository.findAll().stream().map(application -> application.toDto(applicationMapper)).toList();
    }

    @Override
    public List<ApplicationDto> getApplicationsByNameAndLanguage(String name, String language) {
        return applicationRepository.findByNameIgnoreCaseAndLanguageIgnoreCase(name, language)
                .stream().map(application -> application.toDto(applicationMapper)).toList();
    }

    @Override
    public ApplicationDto createApplication(CreateApplicationRequest createApplicationRequest) {
        ApplicationDto applicationDto = applicationMapper.toDto(createApplicationRequest);
        try {
            applicationDto = applicationRepository.saveAndFlush(new Application().fromDto(applicationDto, applicationMapper)).toDto(applicationMapper);
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Application creation failed");
        }
        return applicationDto;
    }

    @Override
    public ApplicationDto updateApplication(Long id, UpdateApplicationRequest updateApplicationRequest) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        ApplicationDto applicationDto = applicationMapper.toDto(updateApplicationRequest);
        application = applicationMapper.partialUpdate(applicationDto, application);
        try {
            applicationDto = applicationRepository.saveAndFlush(application).toDto(applicationMapper);
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Application update failed");
        }
        return applicationDto;
    }

    @Override
    public void deleteApplication(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
        try {
            applicationRepository.delete(application);
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Application delete failed");
        }
    }
}
