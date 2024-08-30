package com.cyberfreak.services.api.request.pagecontent;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddContentItemsRequest implements Serializable {

    @NotNull
    @Size(min = 1)
    @Valid
    private Set<PageContentContentItemRequest> contentItems;
}
