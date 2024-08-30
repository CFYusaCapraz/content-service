package com.cyberfreak.services.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceMapResponse implements Serializable {

    @NotBlank
    private String resourceKey;

    @NotBlank
    private String resourceValue;
}
