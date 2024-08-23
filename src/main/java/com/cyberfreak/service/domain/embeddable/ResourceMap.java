package com.cyberfreak.service.domain.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class ResourceMap {

    @Column(name = "resource_key", nullable = false)
    private String resourceKey;

    @Column(name = "resource_value", nullable = false)
    private String resourceValue;
}