package com.cyberfreak.services.api.response.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse {

    private Boolean success;

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
