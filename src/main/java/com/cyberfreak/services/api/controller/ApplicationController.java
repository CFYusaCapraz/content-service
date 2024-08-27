package com.cyberfreak.services.api.controller;

import com.cyberfreak.services.api.constants.ApiEndpoints;
import com.cyberfreak.services.api.constants.ApiPaths;
import com.cyberfreak.services.api.request.CreateApplicationRequest;
import com.cyberfreak.services.api.request.UpdateApplicationRequest;
import com.cyberfreak.services.api.response.BaseResponse;
import com.cyberfreak.services.api.response.ListResultResponse;
import com.cyberfreak.services.api.response.SaveEntityResponse;
import com.cyberfreak.services.api.response.SingleResultResponse;
import com.cyberfreak.services.dto.ApplicationDto;
import com.cyberfreak.services.service.ApplicationService;
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
    public SingleResultResponse<ApplicationDto> getApplication(@PathVariable(ApiPaths.APPLICATION_ID) Long id) {
        ApplicationDto applicationDto = applicationService.getApplication(id);
        return new SingleResultResponse<>(applicationDto);
    }

    @GetMapping(path = ApiPaths.APPLICATION_SEARCH_PATH)
    public ListResultResponse<ApplicationDto> getApplicationsByNameAndLanguage(@RequestParam(ApiPaths.APPLICATION_NAME) String name,
                                                                               @RequestParam(ApiPaths.APPLICATION_LANGUAGE) String language) {
        List<ApplicationDto> applicationDtoList = applicationService.getApplicationsByNameAndLanguage(name, language);
        return new ListResultResponse<>(applicationDtoList);
    }

    @PostMapping
    public SaveEntityResponse createApplication(@RequestBody CreateApplicationRequest createApplicationRequest) {
        ApplicationDto applicationDto = applicationService.createApplication(createApplicationRequest);
        return new SaveEntityResponse(applicationDto.getId());
    }

    @PutMapping(path = ApiPaths.APPLICATION_ID_PATH)
    public SaveEntityResponse updateApplication(@PathVariable(ApiPaths.APPLICATION_ID) Long id,
                                                @RequestBody UpdateApplicationRequest updateApplicationRequest) {
        ApplicationDto applicationDto = applicationService.updateApplication(id, updateApplicationRequest);
        return new SaveEntityResponse(applicationDto.getId());
    }

    @DeleteMapping(path = ApiPaths.APPLICATION_ID_PATH)
    public BaseResponse deleteApplication(@PathVariable(ApiPaths.APPLICATION_ID) Long id) {
        applicationService.deleteApplication(id);
        return new BaseResponse(true);
    }

}
