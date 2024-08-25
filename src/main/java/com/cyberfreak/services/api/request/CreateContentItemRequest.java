package com.cyberfreak.services.api.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateContentItemRequest implements Serializable {

    private String key;

    private String value;

    private Long applicationId;

    private Long pageId;
}
