package com.cyberfreak.services.api.response;

import com.cyberfreak.services.dto.base.BaseDto;
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
