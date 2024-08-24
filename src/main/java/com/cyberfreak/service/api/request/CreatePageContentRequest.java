package com.cyberfreak.service.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePageContentRequest implements Serializable {

    private String pageName;

    private Long applicationId;
}
