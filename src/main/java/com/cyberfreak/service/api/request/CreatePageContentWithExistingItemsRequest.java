package com.cyberfreak.service.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CreatePageContentWithExistingItemsRequest extends CreatePageContentRequest implements Serializable {
}
