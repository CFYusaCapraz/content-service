package com.cyberfreak.services.api.request.pagecontent;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageContentContentItemRequest implements Serializable {

    @NotBlank
    private String key;

    @NotBlank
    private String value;
}
