package com.cyberfreak.service.repository;

import com.cyberfreak.service.domain.PageContent;
import com.cyberfreak.service.dto.PageContentDto;
import org.springframework.stereotype.Repository;

@Repository
public interface PageContentRepository extends RepositoryBase<PageContent, PageContentDto> {
}