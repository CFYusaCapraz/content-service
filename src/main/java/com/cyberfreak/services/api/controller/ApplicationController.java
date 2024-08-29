package com.cyberfreak.services.api.controller;

import com.cyberfreak.services.api.constants.ApiEndpoints;
import com.cyberfreak.services.api.constants.ApiPaths;
import com.cyberfreak.services.api.request.application.CreateApplicationRequest;
import com.cyberfreak.services.api.request.application.UpdateApplicationRequest;
import com.cyberfreak.services.api.response.base.BaseResponse;
import com.cyberfreak.services.api.response.base.ListResultResponse;
import com.cyberfreak.services.api.response.base.SaveEntityResponse;
import com.cyberfreak.services.api.response.base.SingleResultResponse;
import com.cyberfreak.services.dto.ApplicationDto;
import com.cyberfreak.services.service.ApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ApiEndpoints.APPLICATION_PATH)
@RequiredArgsConstructor
@Slf4j
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public ListResultResponse<ApplicationDto> getApplications() {
        List<ApplicationDto> applicationDtoList = applicationService.getApplications();
        return new ListResultResponse<>(applicationDtoList);
    }

    @GetMapping(path = ApiPaths.APPLICATION_ID_PATH)
    public SingleResultResponse<ApplicationDto> getApplication(
            @NotNull @Positive @PathVariable(ApiPaths.APPLICATION_ID) Long id) {
        ApplicationDto applicationDto = applicationService.getApplication(id);
        return new SingleResultResponse<>(applicationDto);
    }

    @GetMapping(path = ApiPaths.APPLICATION_SEARCH_PATH)
    public ListResultResponse<ApplicationDto> getApplicationsByNameAndLanguage(
            @NotBlank @RequestParam(ApiPaths.APPLICATION_NAME) String name,
            @NotBlank @RequestParam(ApiPaths.APPLICATION_LANGUAGE) String language) {
        List<ApplicationDto> applicationDtoList = applicationService.getApplicationsByNameAndLanguage(name, language);
        return new ListResultResponse<>(applicationDtoList);
    }

    @PostMapping
    public SaveEntityResponse createApplication(
            @Valid @RequestBody CreateApplicationRequest createApplicationRequest) {
        ApplicationDto applicationDto = applicationService.createApplication(createApplicationRequest);
        return new SaveEntityResponse(applicationDto.getId());
    }

    @PutMapping(path = ApiPaths.APPLICATION_ID_PATH)
    public SaveEntityResponse updateApplication(
            @NotNull @Positive @PathVariable(ApiPaths.APPLICATION_ID) Long id,
            @Valid @RequestBody UpdateApplicationRequest updateApplicationRequest) {
        ApplicationDto applicationDto = applicationService.updateApplication(id, updateApplicationRequest);
        return new SaveEntityResponse(applicationDto.getId());
    }

    @DeleteMapping(path = ApiPaths.APPLICATION_ID_PATH)
    public BaseResponse deleteApplication(
            @NotNull @Positive @PathVariable(ApiPaths.APPLICATION_ID) Long id) {
        applicationService.deleteApplication(id);
        return new BaseResponse(true);
    }

}
