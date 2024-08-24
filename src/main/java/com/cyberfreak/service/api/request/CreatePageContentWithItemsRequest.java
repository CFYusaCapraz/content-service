package com.cyberfreak.service.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CreatePageContentWithItemsRequest extends CreatePageContentRequest implements Serializable {

    Set<CreateContentItemRequest> contentItems;
}
