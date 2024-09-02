package com.cyberfreak.services.api.request.contentitem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateContentItemRequest implements Serializable {

    @NotBlank
    private String key;

    @NotBlank
    private String value;

    @NotNull
    @Positive
    private Long applicationId;
}
