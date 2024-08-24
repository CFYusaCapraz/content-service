package com.cyberfreak.services.dto;

import com.cyberfreak.services.dto.base.AuditableDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.cyberfreak.services.domain.Application}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationDto extends AuditableDto implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String language;
}