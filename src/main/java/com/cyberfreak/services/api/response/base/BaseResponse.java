package com.cyberfreak.services.api.response.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse implements Serializable {

    @NotNull
    private Boolean success;

    @NotNull
    @Positive
    private Long timestamp;

    private String responseMessage;

    private String responseCode;

    public BaseResponse(Boolean success, String responseMessage, String responseCode) {
        this.success = success;
        this.timestamp = System.currentTimeMillis();
        this.responseMessage = responseMessage;
        this.responseCode = responseCode;
    }

    public BaseResponse(Boolean success, String responseCode) {
        this(success, responseCode, null);
    }

    public BaseResponse(Boolean success) {
        this(success, null);
    }
}
