package com.cyberfreak.services.api.request.pagecontent;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageContentContentItemRequest implements Serializable {

    private String key;

    private String value;
}
