package com.cyberfreak.service.dto;

import com.cyberfreak.service.dto.base.AuditableDto;
import com.cyberfreak.service.dto.embedded.ResourceMapDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.cyberfreak.service.domain.ContentItem}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentItemDto extends AuditableDto implements Serializable {

    private ResourceMapDto resourceMap;

    private ApplicationDto application;
}