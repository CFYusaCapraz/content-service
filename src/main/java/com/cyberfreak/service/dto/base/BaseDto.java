package com.cyberfreak.service.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.cyberfreak.service.domain.base.BaseEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDto implements Serializable {

    @NotNull
    @Positive
    protected Long id;

    @NotNull
    @PositiveOrZero
    protected Integer version;

    @NotNull
    protected Boolean isDeleted = Boolean.FALSE;
}