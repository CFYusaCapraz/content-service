package com.cyberfreak.services.api.response.base;

import com.cyberfreak.services.dto.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListResultResponse<T extends BaseDto> extends BaseResponse implements Serializable {

    @NotNull
    @Valid
    private List<T> resultList;

    public ListResultResponse(List<T> resultList) {
        super(true);
        this.resultList = resultList;
    }
}
