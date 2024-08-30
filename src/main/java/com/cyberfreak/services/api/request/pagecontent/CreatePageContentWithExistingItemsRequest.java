package com.cyberfreak.services.api.request.pagecontent;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Size(min = 1)
    private Set<Long> contentItems;
}
