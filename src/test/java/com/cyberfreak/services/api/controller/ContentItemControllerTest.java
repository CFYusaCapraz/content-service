package com.cyberfreak.services.api.controller;

import com.cyberfreak.services.api.request.contentitem.CreateContentItemRequest;
import com.cyberfreak.services.api.request.contentitem.UpdateContentItemRequest;
import com.cyberfreak.services.api.response.ContentItemResponse;
import com.cyberfreak.services.config.MainConfig;
import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.mapper.ContentItemMapper;
import com.cyberfreak.services.service.ContentItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// todo: Will implement test cases for invalid request bodies after implementing a base error response handler

@SpringBootTest(classes = MainConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "base_test")
class ContentItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContentItemService contentItemService;

    @MockBean
    private ContentItemMapper contentItemMapper;

    @Test
    void givenValidRequest_whenCreateContentItem_thenReturnSaveEntityResponse() throws Exception {
        // Given
        CreateContentItemRequest createRequest = new CreateContentItemRequest("newKey", "newValue", 1L);
        ContentItemDto contentItemDto = new ContentItemDto();
        contentItemDto.setId(1L);

        Mockito.when(contentItemService.createContentItem(any(CreateContentItemRequest.class)))
                .thenReturn(contentItemDto);

        // When/Then
        mockMvc.perform(post("/api/v1/content-items")
                        .header("x-user-id", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entityId").value(1L));
    }

    @Test
    void whenGetAllContentItems_thenReturnListResultResponse() throws Exception {
        // Given
        ContentItemDto contentItemDto = new ContentItemDto();
        contentItemDto.setId(1L);
        List<ContentItemDto> contentItemDtoList = List.of(contentItemDto);
        ContentItemResponse response = new ContentItemResponse();
        response.setId(1L);

        Mockito.when(contentItemService.getContentItems()).thenReturn(contentItemDtoList);
        Mockito.when(contentItemMapper.toResponse(any(ContentItemDto.class))).thenReturn(response);

        // When/Then
        mockMvc.perform(get("/api/v1/content-items")
                        .header("x-user-id", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.resultList[0].id").value(1L));
    }

    @Test
    void givenValidRequest_whenUpdateContentItem_thenReturnSaveEntityResponse() throws Exception {
        // Given
        UpdateContentItemRequest updateRequest = new UpdateContentItemRequest();
        ContentItemDto contentItemDto = new ContentItemDto();
        contentItemDto.setId(1L);

        Mockito.when(contentItemService.updateContentItem(anyLong(), any(UpdateContentItemRequest.class)))
                .thenReturn(contentItemDto);

        // When/Then
        mockMvc.perform(put("/api/v1/content-items/1")
                        .header("x-user-id", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.entityId").value(1L));
    }

    @Test
    void givenValidId_whenDeleteContentItem_thenReturnBaseResponse() throws Exception {
        // When/Then
        mockMvc.perform(delete("/api/v1/content-items/1")
                        .header("x-user-id", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));

        Mockito.verify(contentItemService, Mockito.times(1)).deleteContentItem(1L);
    }
}
