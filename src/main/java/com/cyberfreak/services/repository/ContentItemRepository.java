package com.cyberfreak.services.repository;

import com.cyberfreak.services.domain.ContentItem;
import com.cyberfreak.services.dto.ContentItemDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentItemRepository extends RepositoryBase<ContentItem, ContentItemDto> {
}