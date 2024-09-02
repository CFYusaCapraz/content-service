package com.cyberfreak.services.service.impl;

import com.cyberfreak.services.api.request.pagecontent.AddContentItemsRequest;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentRequest;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentWithExistingItemsRequest;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentWithItemsRequest;
import com.cyberfreak.services.domain.PageContent;
import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.dto.PageContentDto;
import com.cyberfreak.services.mapper.ContentItemMapper;
import com.cyberfreak.services.mapper.PageContentMapper;
import com.cyberfreak.services.repository.PageContentRepository;
import com.cyberfreak.services.service.PageContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
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
            PageContent pageContent = new PageContent().fromDto(pageContentDto, pageContentMapper);
            pageContentDto = pageContentRepository.saveAndFlush(pageContent).toDto(pageContentMapper);
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Error occurred while creating page content");
        }
        return pageContentDto;
    }

    @Override
    public PageContentDto addContentItemsToPageContent(Long id, AddContentItemsRequest request) {
        PageContentDto pageContentDto = pageContentRepository.findById(id).orElseThrow(() -> new RuntimeException("Page content not found")).toDto(pageContentMapper);
        PageContentDto finalPageContentDto = pageContentDto;
        Set<ContentItemDto> contentItemDtoSet = request.getContentItems().stream()
                .map(contentItemMapper::toDto)
                .peek(contentItemDto -> {
                    contentItemDto.setPage(finalPageContentDto);
                    contentItemDto.setApplication(finalPageContentDto.getApplication());
                })
                .collect(Collectors.toSet());
        if (pageContentDto.getContentItems() != null) {
            pageContentDto.getContentItems().addAll(contentItemDtoSet);
        } else {
            pageContentDto.setContentItems(contentItemDtoSet);
        }
        try {
            PageContent pageContent = new PageContent().fromDto(pageContentDto, pageContentMapper);
            pageContentDto = pageContentRepository.saveAndFlush(pageContent).toDto(pageContentMapper);
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Error occurred while adding content items to page content");
        }
        return pageContentDto;
    }

    @Override
    public PageContentDto createPageContentWithContentItems(CreatePageContentWithItemsRequest request) {
        PageContentDto pageContentDto = pageContentMapper.toDto(request);
        try {
            PageContent pageContent = new PageContent().fromDto(pageContentDto, pageContentMapper);
            pageContentDto = pageContentRepository.saveAndFlush(pageContent).toDto(pageContentMapper);
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
            PageContent pageContent = new PageContent().fromDto(pageContentDto, pageContentMapper);
            pageContentDto = pageContentRepository.saveAndFlush(pageContent).toDto(pageContentMapper);
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Error occurred while creating page content with existing content items");
        }
        return pageContentDto;
    }

    @Override
    public List<PageContentDto> getPageContentList() {
        return pageContentRepository.findAll().stream().map(pageContent -> pageContent.toDto(pageContentMapper)).toList();
    }

    @Override
    public PageContentDto getPageContentByPageName(String pageName) {
        return pageContentRepository.findByPageName(pageName)
                .orElseThrow(() -> new RuntimeException("Page content by page name not found")).toDto(pageContentMapper);
    }

    @Override
    public void deletePageContent(Long id) {
        PageContent pageContent = pageContentRepository.findById(id).orElseThrow(() -> new RuntimeException("Page content not found"));
        try {
            pageContentRepository.delete(pageContent);
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            throw new RuntimeException("Error occurred while deleting page content");
        }
    }

    @Override
    public PageContentDto getPageContentById(@NotNull Long id) {
        return pageContentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Page content not found")).toDto(pageContentMapper);
    }
}
