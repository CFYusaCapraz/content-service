package com.cyberfreak.services.api.request.application;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Application table is needed for being able to differentiate contents of either " +
        "same application name but different language or same language bu different application. " +
        "Ex: Name = <project_name>IOS / Language = EN or Name = <project_name>Web / Language = RU")
public class CreateApplicationRequest implements Serializable {

    @Schema(description = "Name property of application. Must not be blank!")
    @NotBlank
    private String name;

    @Schema(description = "Language property of application. Must not be blank!")
    @NotBlank
    private String language;
}
