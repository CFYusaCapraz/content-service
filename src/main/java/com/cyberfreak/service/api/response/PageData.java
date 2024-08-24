package com.cyberfreak.service.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageData {

    private Integer pageNumber;

    private Integer totalPages;

    private Integer totalRecords;
}
