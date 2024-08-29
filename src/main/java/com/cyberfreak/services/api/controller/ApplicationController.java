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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Application Controller", description = "APIs for managing application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Tag(name = "C(reate) Operations", description = "These APIs create a new Application")
    @Operation(summary = "Create new application",
            description = "Create new application")
    @PostMapping
    public SaveEntityResponse createApplication(
            @Valid @RequestBody CreateApplicationRequest createApplicationRequest) {
        ApplicationDto applicationDto = applicationService.createApplication(createApplicationRequest);
        return new SaveEntityResponse(applicationDto.getId());
    }

    @Tag(name = "R(ead) Operations", description = "These APIs retrieve Applications")
    @Operation(summary = "Get all applications",
            description = "Get all applications")
    @GetMapping
    public ListResultResponse<ApplicationDto> getApplications() {
        List<ApplicationDto> applicationDtoList = applicationService.getApplications();
        return new ListResultResponse<>(applicationDtoList);
    }

    @Tag(name = "R(ead) Operations", description = "These APIs retrieve Applications")
    @Operation(summary = "Get application by its ID",
            description = "ID must not be null and greater than zero")
    @GetMapping(path = ApiPaths.APPLICATION_ID_PATH)
    public SingleResultResponse<ApplicationDto> getApplication(
            @Parameter(description = "ID of the application you want to retrieve. Must be not null and greater than zero")
            @NotNull @Positive @PathVariable(ApiPaths.APPLICATION_ID) Long id) {
        ApplicationDto applicationDto = applicationService.getApplication(id);
        return new SingleResultResponse<>(applicationDto);
    }

    @Tag(name = "R(ead) Operations", description = "These APIs retrieve Applications")
    @Operation(summary = "Get application by either name or language or both",
            description = "This API behaves like \"equal\" when filtering. Provide either one of them or both. " +
                    "If both are provided it will apply \"and\" operation to both filters")
    @GetMapping(path = ApiPaths.APPLICATION_SEARCH_PATH)
    public ListResultResponse<ApplicationDto> getApplicationsByNameAndLanguage(
            @Parameter(description = "Can be null, but not blank")
            @NotBlank @RequestParam(ApiPaths.APPLICATION_NAME) String name,
            @Parameter(description = "Can be null, but not blank")
            @NotBlank @RequestParam(ApiPaths.APPLICATION_LANGUAGE) String language) {
        List<ApplicationDto> applicationDtoList = applicationService.getApplicationsByNameAndLanguage(name, language);
        return new ListResultResponse<>(applicationDtoList);
    }

    @Tag(name = "U(pdate) Operations", description = "These APIs update an already existing Application")
    @Operation(summary = "Update application by ID",
            description = "Even though the HTTP method is PUT it behaves like a partial update(PATCH). " +
                    "So you can only provide the fields you want to update")
    @PutMapping(path = ApiPaths.APPLICATION_ID_PATH)
    public SaveEntityResponse updateApplication(
            @Parameter(description = "ID of the application you want to retrieve. Must be not null and greater than zero")
            @NotNull @Positive @PathVariable(ApiPaths.APPLICATION_ID) Long id,
            @Valid @RequestBody UpdateApplicationRequest updateApplicationRequest) {
        ApplicationDto applicationDto = applicationService.updateApplication(id, updateApplicationRequest);
        return new SaveEntityResponse(applicationDto.getId());
    }

    @Tag(name = "D(elete) Operations", description = "These APIs delete Application")
    @Operation(summary = "Delete application by ID",
            description = "Delete application by ID. This delete operation is soft. " +
                    "Meaning it will not delete the actual Application but, mark it as deleted")
    @DeleteMapping(path = ApiPaths.APPLICATION_ID_PATH)
    public BaseResponse deleteApplication(
            @Parameter(description = "ID of the application you want to retrieve. Must be not null and greater than zero")
            @NotNull @Positive @PathVariable(ApiPaths.APPLICATION_ID) Long id) {
        applicationService.deleteApplication(id);
        return new BaseResponse(true);
    }

}
