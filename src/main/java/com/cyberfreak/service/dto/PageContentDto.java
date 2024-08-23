package com.cyberfreak.service.dto;

import com.cyberfreak.service.dto.base.AuditableDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.cyberfreak.service.domain.PageContent}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageContentDto extends AuditableDto implements Serializable {

    private String pageName;

    private ApplicationDto application;

    private Set<ContentItemDto> contentItems;
}