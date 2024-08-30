package com.cyberfreak.services.api.response.base;

import com.cyberfreak.services.dto.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleResultResponse<T extends BaseDto> extends BaseResponse implements Serializable {

    @NotNull
    @Valid
    private T result;

    public SingleResultResponse(T result) {
        super(true);
        this.result = result;
    }
}
