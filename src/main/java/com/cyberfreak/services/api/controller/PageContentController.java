package com.cyberfreak.services.api.controller;

import com.cyberfreak.services.api.constants.ApiEndpoints;
import com.cyberfreak.services.api.constants.ApiPaths;
import com.cyberfreak.services.api.request.pagecontent.AddContentItemsRequest;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentRequest;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentWithExistingItemsRequest;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentWithItemsRequest;
import com.cyberfreak.services.api.response.PageContentResponse;
import com.cyberfreak.services.api.response.base.ListResultResponse;
import com.cyberfreak.services.api.response.base.SaveEntityResponse;
import com.cyberfreak.services.api.response.base.SingleResultResponse;
import com.cyberfreak.services.dto.PageContentDto;
import com.cyberfreak.services.mapper.PageContentMapper;
import com.cyberfreak.services.service.PageContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ApiEndpoints.PAGE_CONTENT_PATH)
@RequiredArgsConstructor
@Slf4j
public class PageContentController {

    private final PageContentService pageContentService;

    private final PageContentMapper pageContentMapper;

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
    public ListResultResponse<PageContentResponse> getPageContentList() {
        List<PageContentDto> pageContentDtoList = pageContentService.getPageContentList();
        List<PageContentResponse> responseList = pageContentDtoList.stream().map(pageContentMapper::toResponse).toList();
        return new ListResultResponse<>(responseList);
    }

    @GetMapping(path = ApiPaths.PAGE_CONTENT_BY_NAME_PATH)
    public SingleResultResponse<PageContentResponse> getPageContentByPageName(@PathVariable(ApiPaths.PAGE_NAME) String pageName) {
        PageContentDto pageContentDto = pageContentService.getPageContentByPageName(pageName);
        PageContentResponse response = pageContentMapper.toResponse(pageContentDto);
        return new SingleResultResponse<>(response);
    }
}
