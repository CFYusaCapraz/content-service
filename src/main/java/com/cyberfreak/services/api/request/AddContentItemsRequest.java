package com.cyberfreak.services.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddContentItemsRequest implements Serializable {

    private Set<CreateContentItemRequest> contentItems;
}
