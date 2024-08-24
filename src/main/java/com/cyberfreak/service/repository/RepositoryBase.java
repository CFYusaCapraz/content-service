package com.cyberfreak.service.repository;

import com.cyberfreak.service.domain.base.BaseEntity;
import com.cyberfreak.service.dto.base.BaseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryBase<Entity extends BaseEntity<Entity, DTO>, DTO extends BaseDto> extends JpaRepository<Entity, Long>, JpaSpecificationExecutor<Entity> {
}