package com.cyberfreak.services.service.impl;

import com.cyberfreak.services.api.request.AddContentItemsRequest;
import com.cyberfreak.services.api.request.CreatePageContentRequest;
import com.cyberfreak.services.api.request.CreatePageContentWithExistingItemsRequest;
import com.cyberfreak.services.api.request.CreatePageContentWithItemsRequest;
import com.cyberfreak.services.domain.PageContent;
import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.dto.PageContentDto;
import com.cyberfreak.services.mapper.ContentItemMapper;
import com.cyberfreak.services.mapper.PageContentMapper;
import com.cyberfreak.services.repository.PageContentRepository;
import com.cyberfreak.services.service.PageContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PageContentServiceImpl implements PageContentService {

    private final PageContentRepository pageContentRepository;

    private final PageContentMapper pageContentMapper;

    private final ContentItemMapper contentItemMapper;

    @Override
    public PageContentDto createPageContent(CreatePageContentRequest request) {
        PageContentDto pageContentDto = pageContentMapper.toDto(request);
        try {
            pageContentDto = pageContentRepository.saveAndFlush(new PageContent().fromDto(pageContentDto)).toDto();
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Error occurred while creating page content");
        }
        return pageContentDto;
    }

    @Override
    public PageContentDto addContentItemsToPageContent(Long id, AddContentItemsRequest request) {
        PageContentDto pageContentDto = pageContentRepository.findById(id).orElseThrow(() -> new RuntimeException("Page content not found")).toDto();
        PageContentDto finalPageContentDto = pageContentDto;
        Set<ContentItemDto> contentItems = request.getContentItems().stream().map(o -> contentItemMapper.toDto(o, finalPageContentDto.getApplication().getId())).collect(Collectors.toSet());
        pageContentDto.getContentItems().addAll(contentItems);
        try {
            pageContentDto = pageContentRepository.saveAndFlush(new PageContent().fromDto(pageContentDto)).toDto();
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Error occurred while adding content items to page content");
        }
        return pageContentDto;
    }

    @Override
    public PageContentDto createPageContentWithContentItems(CreatePageContentWithItemsRequest request) {
        PageContentDto pageContentDto = pageContentMapper.toDto(request, request.getApplicationId());
        try {
            pageContentDto = pageContentRepository.saveAndFlush(new PageContent().fromDto(pageContentDto)).toDto();
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Error occurred while creating page content with new content items");
        }
        return pageContentDto;
    }

    @Override
    public PageContentDto createPageContentWithExistingContentItems(CreatePageContentWithExistingItemsRequest request) {
        PageContentDto pageContentDto = pageContentMapper.toDto(request);
        try {
            pageContentDto = pageContentRepository.saveAndFlush(new PageContent().fromDto(pageContentDto)).toDto();
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Error occurred while creating page content with existing content items");
        }
        return pageContentDto;
    }
}
