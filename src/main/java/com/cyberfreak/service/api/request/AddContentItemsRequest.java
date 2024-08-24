package com.cyberfreak.service.api.request;

import com.cyberfreak.service.dto.ContentItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddContentItemsRequest implements Serializable {

    private List<ContentItemDto> contentItems;
}
