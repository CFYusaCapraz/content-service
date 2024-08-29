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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ApiEndpoints.PAGE_CONTENT_PATH)
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Page Content Controller", description = "APIs for managing page content")
public class PageContentController {

    private final PageContentService pageContentService;

    private final PageContentMapper pageContentMapper;

    @Tag(name = "C(reate) Operations", description = "These APIs create a new Page Content")
    @Operation(summary = "Creating new page content without any content items",
            description = "This API is implemented if the user wants to create page content first then add content items")
    @PostMapping
    public SaveEntityResponse createPageContent(@RequestBody CreatePageContentRequest request) {
        PageContentDto pageContentDto = pageContentService.createPageContent(request);
        return new SaveEntityResponse(pageContentDto.getId());
    }

    @Tag(name = "U(pdate) Operations", description = "These APIs update an already existing Page Content")
    @Operation(summary = "Add new content items to page content",
            description = "This API is implemented if the user wants to add content items to already existing page content. " +
                    "The content items' application will be set to page content's application")
    @PutMapping(path = ApiPaths.ADD_CONTENT_ITEMS_PATH)
    public SaveEntityResponse addContentItemsToPageContent(
            @Parameter(description = "ID of the page content you want to update", example = "1")
            @PathVariable(ApiPaths.PAGE_CONTENT_ID) Long id,
            @RequestBody AddContentItemsRequest request) {
        PageContentDto pageContentDto = pageContentService.addContentItemsToPageContent(id, request);
        return new SaveEntityResponse(pageContentDto.getId());
    }

    @Tag(name = "C(reate) Operations", description = "These APIs create a new Page Content")
    @Operation(summary = "Creating new page content with new content items",
            description = "This API is implemented if the user wants to create page content with its content items")
    @PostMapping(path = ApiPaths.CREATE_WITH_CONTENT_ITEMS_PATH)
    public SaveEntityResponse createPageContentWithContentItems(@RequestBody CreatePageContentWithItemsRequest request) {
        PageContentDto pageContentDto = pageContentService.createPageContentWithContentItems(request);
        return new SaveEntityResponse(pageContentDto.getId());
    }

    @Tag(name = "C(reate) Operations", description = "These APIs create a new Page Content")
    @Operation(summary = "Creating new page content with existing content items",
            description = "This API is implemented if the user wants to create page content with previously created content items")
    @PostMapping(path = ApiPaths.CREATE_WITH_EXISTING_CONTENT_ITEMS_PATH)
    public SaveEntityResponse createPageContentWithExistingContentItems(@RequestBody CreatePageContentWithExistingItemsRequest request) {
        PageContentDto pageContentDto = pageContentService.createPageContentWithExistingContentItems(request);
        return new SaveEntityResponse(pageContentDto.getId());
    }

    @Tag(name = "R(ead) Operations", description = "These APIs retrieve Page Contents")
    @Operation(summary = "Get all page contents",
            description = "This API returns all of the page contents with the content items included")
    @GetMapping
    public ListResultResponse<PageContentResponse> getPageContentList() {
        List<PageContentDto> pageContentDtoList = pageContentService.getPageContentList();
        List<PageContentResponse> responseList = pageContentDtoList.stream().map(pageContentMapper::toResponse).toList();
        return new ListResultResponse<>(responseList);
    }

    @Tag(name = "R(ead) Operations", description = "These APIs retrieve Page Contents")
    @Operation(summary = "Get a page content with its name",
            description = "This API returns the page content with the provided pageName path variable with the content items included. " +
                    "This API behaves like \"equal\" operator not \"like\" operator. So do not expect any result if the provided pageName is not fully qualified")
    @GetMapping(path = ApiPaths.PAGE_CONTENT_BY_NAME_PATH)
    public SingleResultResponse<PageContentResponse> getPageContentByPageName(
            @Parameter(description = "Page Name of the page content you want to retrieve", example = "HomePage")
            @PathVariable(ApiPaths.PAGE_NAME) String pageName) {
        PageContentDto pageContentDto = pageContentService.getPageContentByPageName(pageName);
        PageContentResponse response = pageContentMapper.toResponse(pageContentDto);
        return new SingleResultResponse<>(response);
    }
}
