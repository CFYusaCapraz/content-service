package com.cyberfreak.services.api.response.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveEntityResponse extends BaseResponse {

    private Long entityId;

    public SaveEntityResponse(Long entityId) {
        super(true);
        this.entityId = entityId;
    }
}
