package com.cyberfreak.services.repository;

import com.cyberfreak.services.domain.Application;
import com.cyberfreak.services.dto.ApplicationDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends RepositoryBase<Application, ApplicationDto> {
}