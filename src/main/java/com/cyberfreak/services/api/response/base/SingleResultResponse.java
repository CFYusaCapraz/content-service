package com.cyberfreak.services.api.response.base;

import com.cyberfreak.services.dto.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleResultResponse<T extends BaseDto> extends BaseResponse {

    private T result;

    public SingleResultResponse(T result) {
        super(true);
        this.result = result;
    }
}
