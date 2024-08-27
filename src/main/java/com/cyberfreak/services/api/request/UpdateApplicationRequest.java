package com.cyberfreak.services.api.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationRequest implements Serializable {

    @Pattern(regexp = "^(?!\\s*$).+", message = "must not be blank")
    private String name;

    @Pattern(regexp = "^(?!\\s*$).+", message = "must not be blank")
    private String language;
}
