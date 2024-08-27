package com.cyberfreak.services.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateApplicationRequest implements Serializable {

    private String name;

    private String language;
}
