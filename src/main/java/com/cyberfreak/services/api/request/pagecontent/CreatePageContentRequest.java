package com.cyberfreak.services.api.request.pagecontent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePageContentRequest implements Serializable {

    protected String pageName;

    protected Long applicationId;
}
