package com.cyberfreak.services.api.response.base;

import com.cyberfreak.services.dto.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagedListResultResponse<T extends BaseDto> extends BaseResponse implements Serializable {

    private List<T> resultList;

    private PageData pageData;

    public PagedListResultResponse(List<T> resultList, PageData pageData) {
        super(true);
        this.resultList = resultList;
        this.pageData = pageData;
    }
}
