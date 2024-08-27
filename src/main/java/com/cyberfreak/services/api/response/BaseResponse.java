package com.cyberfreak.services.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    private Boolean success;

    private String message;

    public BaseResponse(Boolean success) {
        this.success = success;
    }
}
