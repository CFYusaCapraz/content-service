package com.cyberfreak.services.repository;

import com.cyberfreak.services.domain.Application;
import com.cyberfreak.services.dto.ApplicationDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends RepositoryBase<Application, ApplicationDto> {
    @Query("select a from Application a " +
            "where (:name is not null or :language is not null ) " +
            "and (:name is null or upper(a.name) = upper(:name)) " +
            "and (:language is null or upper(a.language) = upper(:language))")
    List<Application> findByNameIgnoreCaseAndLanguageIgnoreCase(@Param("name") @Nullable String name, @Param("language") @Nullable String language);
}