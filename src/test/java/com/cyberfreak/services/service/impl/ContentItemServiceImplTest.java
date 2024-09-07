package com.cyberfreak.services.service.impl;

import com.cyberfreak.services.api.context.CycleAvoidingMappingContext;
import com.cyberfreak.services.api.request.contentitem.CreateContentItemRequest;
import com.cyberfreak.services.api.request.contentitem.UpdateContentItemRequest;
import com.cyberfreak.services.domain.ContentItem;
import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.mapper.ContentItemMapper;
import com.cyberfreak.services.repository.ContentItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContentItemServiceImplTest {

    @Mock
    private ContentItemRepository contentItemRepository;

    @Mock
    private ContentItemMapper contentItemMapper;

    @InjectMocks
    private ContentItemServiceImpl contentItemService;

    public ContentItemServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenValidId_whenGetContentItem_thenReturnContentItemDto() {
        // Given
        Long id = 1L;
        ContentItem contentItem = new ContentItem();
        contentItem.setId(id);
        ContentItemDto contentItemDto = new ContentItemDto();
        contentItemDto.setId(id);

        // Stubbing
        when(contentItemRepository.findById(id)).thenReturn(Optional.of(contentItem));
        when(contentItemMapper.toDto(any(), any())).thenReturn(contentItemDto);

        // When
        ContentItemDto result = contentItemService.getContentItem(id);

        // Then
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(contentItemRepository, times(1)).findById(id);
    }

    @Test
    void givenInvalidId_whenGetContentItem_thenThrowException() {
        // Given
        Long id = 1L;
        when(contentItemRepository.findById(id)).thenReturn(Optional.empty());

        // When/Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> contentItemService.getContentItem(id));
        assertEquals("Content item not found", exception.getMessage());
        verify(contentItemRepository, times(1)).findById(id);
    }

    @Test
    void givenValidRequest_whenCreateContentItem_thenReturnContentItemDto() {
        // Given
        CreateContentItemRequest request = new CreateContentItemRequest();
        ContentItemDto contentItemDto = new ContentItemDto();
        ContentItem contentItem = new ContentItem();

        when(contentItemMapper.toDto(request)).thenReturn(contentItemDto);
        when(contentItemRepository.saveAndFlush(any(ContentItem.class))).thenReturn(contentItem);
        when(contentItemMapper.toEntity(any(ContentItemDto.class), any(CycleAvoidingMappingContext.class))).thenReturn(contentItem);
        when(contentItemMapper.toDto(any(ContentItem.class), any(CycleAvoidingMappingContext.class))).thenReturn(contentItemDto);

        // When
        ContentItemDto result = contentItemService.createContentItem(request);

        // Then
        assertNotNull(result);
        verify(contentItemRepository, times(1)).saveAndFlush(any(ContentItem.class));
    }

    @Test
    void givenException_whenCreateContentItem_thenThrowException() {
        // Given
        CreateContentItemRequest request = new CreateContentItemRequest();
        ContentItemDto contentItemDto = new ContentItemDto();

        when(contentItemMapper.toDto(request)).thenReturn(contentItemDto);
        when(contentItemRepository.saveAndFlush(any(ContentItem.class))).thenThrow(new RuntimeException("Save failed"));

        // When/Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> contentItemService.createContentItem(request));
        assertEquals("Content item creation failed", exception.getMessage());
    }

    @Test
    void whenGetContentItems_thenReturnListOfContentItemDtos() {
        // Given
        List<ContentItem> contentItems = List.of(new ContentItem(), new ContentItem());
        when(contentItemRepository.findAll()).thenReturn(contentItems);

        // When
        List<ContentItemDto> contentItemDtos = contentItemService.getContentItems();

        // Then
        assertNotNull(contentItemDtos);
        assertEquals(2, contentItemDtos.size());
        verify(contentItemRepository, times(1)).findAll();
    }

    @Test
    void givenValidIdAndRequest_whenUpdateContentItem_thenReturnUpdatedContentItemDto() {
        // Given
        Long id = 1L;
        UpdateContentItemRequest request = new UpdateContentItemRequest();
        ContentItem contentItem = new ContentItem();
        ContentItemDto contentItemDto = new ContentItemDto();

        when(contentItemRepository.findById(id)).thenReturn(Optional.of(contentItem));
        when(contentItemMapper.toDto(request)).thenReturn(contentItemDto);
        when(contentItemMapper.partialUpdate(any(ContentItemDto.class), any(ContentItem.class), any(CycleAvoidingMappingContext.class)))
                .thenReturn(contentItem);
        when(contentItemRepository.saveAndFlush(contentItem)).thenReturn(contentItem);
        when(contentItemMapper.toDto(any(),any())).thenReturn(contentItemDto);

        // When
        ContentItemDto result = contentItemService.updateContentItem(id, request);

        // Then
        assertNotNull(result);
        verify(contentItemRepository, times(1)).findById(id);
        verify(contentItemRepository, times(1)).saveAndFlush(contentItem);
    }

    @Test
    void givenInvalidId_whenUpdateContentItem_thenThrowException() {
        // Given
        Long id = 1L;
        UpdateContentItemRequest request = new UpdateContentItemRequest();
        when(contentItemRepository.findById(id)).thenReturn(Optional.empty());

        // When/Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> contentItemService.updateContentItem(id, request));
        assertEquals("Content item not found", exception.getMessage());
        verify(contentItemRepository, times(1)).findById(id);
    }

    @Test
    void givenValidId_whenDeleteContentItem_thenDeleteContentItem() {
        // Given
        Long id = 1L;
        ContentItem contentItem = new ContentItem();
        contentItem.setIsDeleted(false);

        when(contentItemRepository.findById(id)).thenReturn(Optional.of(contentItem));

        // When
        contentItemService.deleteContentItem(id);

        // Then
        verify(contentItemRepository, times(1)).delete(contentItem);
    }

    @Test
    void givenDeletedContentItem_whenDeleteContentItem_thenThrowException() {
        // Given
        Long id = 1L;
        ContentItem contentItem = new ContentItem();
        contentItem.setIsDeleted(true);

        when(contentItemRepository.findById(id)).thenReturn(Optional.of(contentItem));

        // When/Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> contentItemService.deleteContentItem(id));
        assertEquals("Content item is already deleted", exception.getMessage());
        verify(contentItemRepository, never()).delete(contentItem);
    }

    @Test
    void givenInvalidId_whenDeleteContentItem_thenThrowException() {
        // Given
        Long id = 1L;
        when(contentItemRepository.findById(id)).thenReturn(Optional.empty());

        // When/Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> contentItemService.deleteContentItem(id));
        assertEquals("Content item not found", exception.getMessage());
        verify(contentItemRepository, times(1)).findById(id);
    }
}
