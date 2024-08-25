package com.cyberfreak.services.api.request;

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
public class CreatePageContentWithItemsRequest extends CreatePageContentRequest implements Serializable {

    private Set<CreateContentItemRequest> contentItems;
}
