package com.cyberfreak.services.repository;

import com.cyberfreak.services.domain.base.BaseEntity;
import com.cyberfreak.services.dto.base.BaseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryBase<Entity extends BaseEntity<Entity, DTO>, DTO extends BaseDto> extends JpaRepository<Entity, Long>, JpaSpecificationExecutor<Entity> {
}