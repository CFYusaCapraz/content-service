package com.cyberfreak.services.service;

import com.cyberfreak.services.api.request.contentitem.CreateContentItemRequest;
import com.cyberfreak.services.dto.ContentItemDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Named;

public interface ContentItemService {

    @Named("mapContentItemIdToContentItemDto")
    ContentItemDto getContentItem(@NotNull Long id);

    ContentItemDto createContentItem(CreateContentItemRequest createContentItemRequest);
}
