package com.cyberfreak.services.api.request.application;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationRequest implements Serializable {

    @Schema(defaultValue = "ContentManagement_Web")
    @Pattern(regexp = "^(?!\\s*$).+", message = "must not be blank")
    private String name;

    @Schema(defaultValue = "EN")
    @Pattern(regexp = "^(?!\\s*$).+", message = "must not be blank")
    private String language;
}
