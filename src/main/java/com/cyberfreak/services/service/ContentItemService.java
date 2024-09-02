package com.cyberfreak.services.service;

import com.cyberfreak.services.api.request.contentitem.CreateContentItemRequest;
import com.cyberfreak.services.api.request.contentitem.UpdateContentItemRequest;
import com.cyberfreak.services.dto.ContentItemDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Named;

import java.util.List;

public interface ContentItemService {

    @Named("mapContentItemIdToContentItemDto")
    ContentItemDto getContentItem(@NotNull Long id);

    ContentItemDto createContentItem(CreateContentItemRequest createContentItemRequest);

    List<ContentItemDto> getContentItems();

    ContentItemDto updateContentItem(Long id, UpdateContentItemRequest updateContentItemRequest);
}
