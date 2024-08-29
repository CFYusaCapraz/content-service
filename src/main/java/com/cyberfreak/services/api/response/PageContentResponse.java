package com.cyberfreak.services.api.response;

import com.cyberfreak.services.api.response.light.ApplicationLightResponse;
import com.cyberfreak.services.api.response.light.ContentItemLightResponse;
import com.cyberfreak.services.dto.base.AuditableDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PageContentResponse extends AuditableDto implements Serializable {

    private String pageName;

    private ApplicationLightResponse application;

    private Set<ContentItemLightResponse> contentItems;
}
