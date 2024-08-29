package com.cyberfreak.services.api.response.base;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageData implements Serializable {

    @NotNull
    @Positive
    private Integer pageNumber;

    @NotNull
    @Positive
    private Integer totalPages;

    @NotNull
    @Positive
    private Integer totalRecords;
}
