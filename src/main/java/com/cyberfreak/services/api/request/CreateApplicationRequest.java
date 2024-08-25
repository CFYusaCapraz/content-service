package com.cyberfreak.services.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateApplicationRequest implements Serializable {

    @NotBlank
    private String name;

    @NotBlank
    private String language;
}
