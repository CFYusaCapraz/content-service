package com.cyberfreak.service.api.controller;

import com.cyberfreak.service.api.constants.ApiEndpoints;
import com.cyberfreak.service.api.constants.ApiPaths;
import com.cyberfreak.service.api.request.AddContentItemsRequest;
import com.cyberfreak.service.api.request.CreatePageContentRequest;
import com.cyberfreak.service.api.request.CreatePageContentWithExistingItemsRequest;
import com.cyberfreak.service.api.request.CreatePageContentWithItemsRequest;
import com.cyberfreak.service.api.response.ListResultResponse;
import com.cyberfreak.service.api.response.SaveEntityResponse;
import com.cyberfreak.service.api.response.SingleResultResponse;
import com.cyberfreak.service.dto.PageContentDto;
import com.cyberfreak.service.service.PageContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = ApiEndpoints.PAGE_CONTENT_PATH)
@RequiredArgsConstructor
@Slf4j
public class PageContentController {

    private final PageContentService pageContentService;

    @PostMapping
    public SaveEntityResponse createPageContent(@RequestBody CreatePageContentRequest request) {
        PageContentDto pageContentDto = pageContentService.createPageContent(request);
        return new SaveEntityResponse(pageContentDto.getId());
    }

    @PutMapping(path = ApiPaths.ADD_CONTENT_ITEMS_PATH)
    public SaveEntityResponse addContentItemsToPageContent(@PathVariable(ApiPaths.PAGE_CONTENT_ID) Long id,
                                                           @RequestBody AddContentItemsRequest request) {
        PageContentDto pageContentDto = pageContentService.addContentItemsToPageContent(id, request);
        return new SaveEntityResponse(pageContentDto.getId());
    }

    @PostMapping(path = ApiPaths.CREATE_WITH_CONTENT_ITEMS_PATH)
    public SaveEntityResponse createPageContentWithContentItems(@RequestBody CreatePageContentWithItemsRequest request) {
        PageContentDto pageContentDto = pageContentService.createPageContentWithContentItems(request);
        return new SaveEntityResponse(pageContentDto.getId());
    }

    @PostMapping(path = ApiPaths.CREATE_WITH_EXISTING_CONTENT_ITEMS_PATH)
    public SaveEntityResponse createPageContentWithExistingContentItems(@RequestBody CreatePageContentWithExistingItemsRequest request) {
        PageContentDto pageContentDto = pageContentService.createPageContentWithExistingContentItems(request);
        return new SaveEntityResponse(pageContentDto.getId());
    }

    @GetMapping
    public ListResultResponse<PageContentDto> getPageContentList() {
        return new ListResultResponse<>(null);
    }

    @GetMapping(path = "/by{PageName}")
    public SingleResultResponse<PageContentDto> getPageContentByPageName(
            @PathVariable(name = "PageName") String pageName) {
        return new SingleResultResponse<>(null);
    }
}
