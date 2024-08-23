package com.cyberfreak.service.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.cyberfreak.service.domain.base.AuditableEntity}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AuditableDto extends BaseDto implements Serializable {

    @NotNull
    private LocalDateTime creationTime;

    private LocalDateTime modificationTime;

    private LocalDateTime deletionTime;

    @NotNull
    private String createdBy;

    private String modifiedBy;

    private String deletedBy;
}