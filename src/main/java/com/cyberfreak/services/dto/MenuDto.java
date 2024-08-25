package com.cyberfreak.services.dto;

import com.cyberfreak.services.dto.base.AuditableDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.cyberfreak.services.domain.Menu}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuDto extends AuditableDto implements Serializable {

    private ApplicationDto application;

    private List<MenuItemDto> menuItems;
}