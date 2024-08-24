package com.cyberfreak.service.service.impl;

import com.cyberfreak.service.api.request.AddContentItemsRequest;
import com.cyberfreak.service.api.request.CreatePageContentRequest;
import com.cyberfreak.service.api.request.CreatePageContentWithExistingItemsRequest;
import com.cyberfreak.service.api.request.CreatePageContentWithItemsRequest;
import com.cyberfreak.service.domain.PageContent;
import com.cyberfreak.service.dto.ContentItemDto;
import com.cyberfreak.service.dto.PageContentDto;
import com.cyberfreak.service.mapper.ContentItemMapper;
import com.cyberfreak.service.mapper.PageContentMapper;
import com.cyberfreak.service.repository.PageContentRepository;
import com.cyberfreak.service.service.PageContentService;
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
