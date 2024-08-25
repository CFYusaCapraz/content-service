package com.cyberfreak.services.api.response;

import com.cyberfreak.services.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleResultResponse<T extends BaseDto> {

    private T result;
}
