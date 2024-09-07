package com.cyberfreak.services.api.controller;


import com.cyberfreak.services.api.constants.ApiEndpoints;
import com.cyberfreak.services.api.constants.ApiPaths;
import com.cyberfreak.services.api.request.application.UpdateApplicationRequest;
import com.cyberfreak.services.api.request.contentitem.CreateContentItemRequest;
import com.cyberfreak.services.api.request.contentitem.UpdateContentItemRequest;
import com.cyberfreak.services.api.response.ContentItemResponse;
import com.cyberfreak.services.api.response.base.BaseResponse;
import com.cyberfreak.services.api.response.base.ListResultResponse;
import com.cyberfreak.services.api.response.base.SaveEntityResponse;
import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.mapper.ContentItemMapper;
import com.cyberfreak.services.service.ContentItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ApiEndpoints.CONTENT_ITEM_PATH)
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Content Item Controller", description = "APIs for managing content items")
public class ContentItemController {

    private final ContentItemService contentItemService;

    private final ContentItemMapper contentItemMapper;

    @Tag(name = "C(reate) Operations", description = "These APIs create a new Content Item")
    @Operation(summary = "Create new content item",
            description = "Create new content item")
    @PostMapping
    public SaveEntityResponse createContentItem(
            @Valid @RequestBody CreateContentItemRequest createContentItemRequest) {
        ContentItemDto contentItemDto = contentItemService.createContentItem(createContentItemRequest);
        return new SaveEntityResponse(contentItemDto.getId());
    }

    @Tag(name = "R(ead) Operations", description = "These APIs retrieve Content Items")
    @Operation(summary = "Get all content items",
            description = "Get all content items")
    @GetMapping
    public ListResultResponse<ContentItemResponse> getContentItems() {
        List<ContentItemDto> contentItemDtoList = contentItemService.getContentItems();
        List<ContentItemResponse> responseList = contentItemDtoList.stream().map(contentItemMapper::toResponse).toList();
        return new ListResultResponse<>(responseList);
    }

    @Tag(name = "U(pdate) Operations", description = "These APIs update an already existing Content Item")
    @Operation(summary = "Update content item by ID",
            description = "Even though the HTTP method is PUT it behaves like a partial update(PATCH). " +
                    "So you can only provide the fields you want to update")
    @PutMapping(path = ApiPaths.CONTENT_ITEM_ID_PATH)
    public SaveEntityResponse updateContentItem(
            @Parameter(description = "ID of the content item you want to retrieve. Must be not null and greater than zero")
            @NotNull @Positive @PathVariable(ApiPaths.CONTENT_ITEM_ID) Long id,
            @Valid @RequestBody UpdateContentItemRequest updateContentItemRequest) {
        ContentItemDto contentItemDto = contentItemService.updateContentItem(id, updateContentItemRequest);
        return new SaveEntityResponse(contentItemDto.getId());
    }

    @Tag(name = "D(elete) Operations", description = "These APIs delete Content Item")
    @Operation(summary = "Delete content item by ID",
            description = "Delete content item by ID. This delete operation is soft. " +
                    "Meaning it will not delete the actual content item but, mark it as deleted")
    @DeleteMapping(path = ApiPaths.CONTENT_ITEM_ID_PATH)
    public BaseResponse deleteContentItem(
            @Parameter(description = "ID of the content item you want to retrieve. Must be not null and greater than zero")
            @NotNull @Positive @PathVariable(ApiPaths.CONTENT_ITEM_ID) Long id) {
        contentItemService.deleteContentItem(id);
        return new BaseResponse(true);
    }
}
