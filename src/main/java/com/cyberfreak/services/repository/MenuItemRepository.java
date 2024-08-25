package com.cyberfreak.services.repository;

import com.cyberfreak.services.domain.MenuItem;
import com.cyberfreak.services.dto.MenuItemDto;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends RepositoryBase<MenuItem, MenuItemDto> {
}