package com.cyberfreak.service.service.impl;

import com.cyberfreak.service.api.request.AddContentItemsRequest;
import com.cyberfreak.service.api.request.CreatePageContentRequest;
import com.cyberfreak.service.api.request.CreatePageContentWithExistingItemsRequest;
import com.cyberfreak.service.api.request.CreatePageContentWithItemsRequest;
import com.cyberfreak.service.domain.PageContent;
import com.cyberfreak.service.dto.PageContentDto;
import com.cyberfreak.service.mapper.PageContentMapper;
import com.cyberfreak.service.repository.PageContentRepository;
import com.cyberfreak.service.service.PageContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PageContentServiceImpl implements PageContentService {

    private final PageContentRepository pageContentRepository;

    private final PageContentMapper pageContentMapper;

    @Override
    public PageContentDto createPageContent(CreatePageContentRequest request) {
        PageContentDto pageContentDto = pageContentMapper.toDTO(request);
        PageContent pageContent = new PageContent().fromDTO(pageContentDto);
        try {
            pageContentDto = pageContentRepository.saveAndFlush(pageContent).toDTO();
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Error occurred while creating page content");
        }
        return pageContentDto;
    }

    @Override
    public PageContentDto addContentItemsToPageContent(Long id, AddContentItemsRequest request) {
        return null;
    }

    @Override
    public PageContentDto createPageContentWithContentItems(CreatePageContentWithItemsRequest request) {
        return null;
    }

    @Override
    public PageContentDto createPageContentWithExistingContentItems(CreatePageContentWithExistingItemsRequest request) {
        return null;
    }
}
