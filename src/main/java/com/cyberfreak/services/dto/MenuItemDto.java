package com.cyberfreak.services.dto;

import com.cyberfreak.services.dto.base.AuditableDto;
import com.cyberfreak.services.dto.embedded.ResourceMapDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.cyberfreak.services.domain.MenuItem}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuItemDto extends AuditableDto implements Serializable {

    private ResourceMapDto resourceMap;

    private ApplicationDto application;

    private Boolean active = Boolean.TRUE;

    private Integer orderIndex;

    private MenuItemDto parent;

    private List<MenuItemDto> children;

    private MenuDto menu;
}