package com.cyberfreak.services.api.request.pagecontent;

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
public class CreatePageContentRequest implements Serializable {

    @NotBlank
    protected String pageName;

    @NotNull
    @Positive
    protected Long applicationId;
}
