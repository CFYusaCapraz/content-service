package com.cyberfreak.services.service.impl;

import com.cyberfreak.services.api.request.contentitem.CreateContentItemRequest;
import com.cyberfreak.services.domain.ContentItem;
import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.mapper.ContentItemMapper;
import com.cyberfreak.services.repository.ContentItemRepository;
import com.cyberfreak.services.service.ContentItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentItemServiceImpl implements ContentItemService {

    private final ContentItemRepository contentItemRepository;

    private final ContentItemMapper contentItemMapper;

    @Override
    public ContentItemDto getContentItem(@NotNull Long id) {
        return contentItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Content item not found")).toDto(contentItemMapper);
    }

    @Override
    public ContentItemDto createContentItem(CreateContentItemRequest createContentItemRequest) {
        ContentItemDto contentItemDto = contentItemMapper.toDto(createContentItemRequest);
        try {
            ContentItem contentItem = new ContentItem().fromDto(contentItemDto, contentItemMapper);
            contentItemDto = contentItemRepository.saveAndFlush(contentItem).toDto(contentItemMapper);
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Content item creation failed");
        }
        return contentItemDto;
    }
}
