package com.cyberfreak.service.api.response;

import com.cyberfreak.service.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListResultResponse<T extends BaseDto> {

    private List<T> resultList;
}
