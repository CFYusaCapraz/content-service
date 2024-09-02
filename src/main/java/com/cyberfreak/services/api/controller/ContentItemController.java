package com.cyberfreak.services.api.controller;


import com.cyberfreak.services.api.constants.ApiEndpoints;
import com.cyberfreak.services.api.request.contentitem.CreateContentItemRequest;
import com.cyberfreak.services.api.response.ContentItemResponse;
import com.cyberfreak.services.api.response.base.ListResultResponse;
import com.cyberfreak.services.api.response.base.SaveEntityResponse;
import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.mapper.ContentItemMapper;
import com.cyberfreak.services.service.ContentItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
}
