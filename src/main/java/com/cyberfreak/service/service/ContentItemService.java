package com.cyberfreak.service.service;

import com.cyberfreak.service.dto.ContentItemDto;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Named;

public interface ContentItemService {

    @Named("mapContentItemIdToContentItemDto")
    ContentItemDto getContentItem(@NotNull Long id);
}
