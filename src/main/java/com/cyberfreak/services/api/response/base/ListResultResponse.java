package com.cyberfreak.services.api.response.base;

import com.cyberfreak.services.dto.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListResultResponse<T extends BaseDto> extends BaseResponse {

    private List<T> resultList;

    public ListResultResponse(List<T> resultList) {
        super(true);
        this.resultList = resultList;
    }
}
