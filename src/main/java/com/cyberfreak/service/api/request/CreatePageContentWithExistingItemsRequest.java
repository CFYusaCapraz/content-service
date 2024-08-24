package com.cyberfreak.service.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePageContentWithExistingItemsRequest extends CreatePageContentRequest implements Serializable {

    private Set<Long> contentItems;
}
