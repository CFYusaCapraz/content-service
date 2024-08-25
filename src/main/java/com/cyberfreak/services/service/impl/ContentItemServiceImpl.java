package com.cyberfreak.services.service.impl;

import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.service.ContentItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContentItemServiceImpl implements ContentItemService {
    @Override
    public ContentItemDto getContentItem(@NotNull Long id) {
        return null;
    }
}
