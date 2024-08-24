package com.cyberfreak.service.service;

import com.cyberfreak.service.api.request.AddContentItemsRequest;
import com.cyberfreak.service.api.request.CreatePageContentRequest;
import com.cyberfreak.service.api.request.CreatePageContentWithExistingItemsRequest;
import com.cyberfreak.service.api.request.CreatePageContentWithItemsRequest;
import com.cyberfreak.service.dto.PageContentDto;

public interface PageContentService {

    PageContentDto createPageContent(CreatePageContentRequest request);

    PageContentDto addContentItemsToPageContent(Long id, AddContentItemsRequest request);

    PageContentDto createPageContentWithContentItems(CreatePageContentWithItemsRequest request);

    PageContentDto createPageContentWithExistingContentItems(CreatePageContentWithExistingItemsRequest request);
}
