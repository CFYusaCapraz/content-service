package com.cyberfreak.services.api.response.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveEntityResponse extends BaseResponse implements Serializable {

    @NotNull
    @Positive
    private Long entityId;

    public SaveEntityResponse(Long entityId) {
        super(true);
        this.entityId = entityId;
    }
}
