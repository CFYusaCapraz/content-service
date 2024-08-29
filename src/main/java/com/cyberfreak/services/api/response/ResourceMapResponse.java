package com.cyberfreak.services.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceMapResponse implements Serializable {

    private String resourceKey;

    private String resourceValue;
}
