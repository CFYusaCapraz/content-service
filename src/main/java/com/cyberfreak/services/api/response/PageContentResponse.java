package com.cyberfreak.services.api.response;

import com.cyberfreak.services.api.response.light.ApplicationLightResponse;
import com.cyberfreak.services.api.response.light.ContentItemLightResponse;
import com.cyberfreak.services.dto.base.AuditableDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PageContentResponse extends AuditableDto implements Serializable {

    @NotBlank
    private String pageName;

    @NotNull
    @Valid
    private ApplicationLightResponse application;

    @NotNull
    @Valid
    private Set<ContentItemLightResponse> contentItems;
}
