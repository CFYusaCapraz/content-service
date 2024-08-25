package com.cyberfreak.services.repository;

import com.cyberfreak.services.domain.PageContent;
import com.cyberfreak.services.dto.PageContentDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageContentRepository extends RepositoryBase<PageContent, PageContentDto> {

    @Query("select p from PageContent p where p.pageName = :pageName")
    @NonNull
    Optional<PageContent> findByPageName(@Param("pageName") @NonNull String pageName);
}