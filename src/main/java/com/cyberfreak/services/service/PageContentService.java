package com.cyberfreak.services.service;

import com.cyberfreak.services.api.request.pagecontent.AddContentItemsRequest;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentRequest;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentWithExistingItemsRequest;
import com.cyberfreak.services.api.request.pagecontent.CreatePageContentWithItemsRequest;
import com.cyberfreak.services.dto.PageContentDto;

import java.util.List;

public interface PageContentService {

    PageContentDto createPageContent(CreatePageContentRequest request);

    PageContentDto addContentItemsToPageContent(Long id, AddContentItemsRequest request);

    PageContentDto createPageContentWithContentItems(CreatePageContentWithItemsRequest request);

    PageContentDto createPageContentWithExistingContentItems(CreatePageContentWithExistingItemsRequest request);

    List<PageContentDto> getPageContentList();

    PageContentDto getPageContentByPageName(String pageName);
}
