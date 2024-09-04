package com.cyberfreak.services.api.request.contentitem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContentItemRequest implements Serializable {

    private String key;

    private String value;

    private Long applicationId;

    private Long pageId;
}
