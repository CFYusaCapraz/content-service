package com.cyberfreak.services.repository;

import com.cyberfreak.services.domain.Menu;
import com.cyberfreak.services.dto.MenuDto;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends RepositoryBase<Menu, MenuDto> {
}