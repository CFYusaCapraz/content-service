package com.cyberfreak.services.api.controller;


import com.cyberfreak.services.api.constants.ApiEndpoints;
import com.cyberfreak.services.service.ContentItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ApiEndpoints.CONTENT_ITEM_PATH)
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Content Item Controller", description = "APIs for managing content items")
public class ContentItemController {

    private final ContentItemService contentItemService;
}
