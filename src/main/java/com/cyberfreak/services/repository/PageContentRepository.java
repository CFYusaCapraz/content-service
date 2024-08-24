package com.cyberfreak.services.repository;

import com.cyberfreak.services.domain.PageContent;
import com.cyberfreak.services.dto.PageContentDto;
import org.springframework.stereotype.Repository;

@Repository
public interface PageContentRepository extends RepositoryBase<PageContent, PageContentDto> {
}