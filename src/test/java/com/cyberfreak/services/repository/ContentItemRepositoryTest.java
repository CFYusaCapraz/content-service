package com.cyberfreak.services.repository;

import com.cyberfreak.services.api.context.UserContext;
import com.cyberfreak.services.config.MainConfig;
import com.cyberfreak.services.domain.Application;
import com.cyberfreak.services.domain.ContentItem;
import com.cyberfreak.services.domain.PageContent;
import com.cyberfreak.services.domain.embeddable.ResourceMap;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = {MainConfig.class})
@ActiveProfiles(profiles = "base_test")
class ContentItemRepositoryTest {

    @Autowired
    private ContentItemRepository contentItemRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Application application;

    @BeforeEach
    void setUp() {
        UserContext.setCurrentUserId("test_user");

        application = new Application();
        application.setName("Test Application");
        application.setLanguage("EN");
        entityManager.persistAndFlush(application);
    }

    @Test
    void givenValidContentItem_whenSaveAndFlush_thenReturnPersistedEntity() {
        // Given
        ResourceMap resourceMap = new ResourceMap();
        resourceMap.setResourceKey("resourceKey");
        resourceMap.setResourceValue("resourceValue");
        ContentItem contentItem = new ContentItem();
        contentItem.setApplication(application);
        contentItem.setResourceMap(resourceMap);

        // When
        ContentItem savedContentItem = contentItemRepository.saveAndFlush(contentItem);

        // Then
        assertNotNull(savedContentItem);
        assertTrue(savedContentItem.getId() > 0);

        ContentItem retrievedContentItem = contentItemRepository.findById(savedContentItem.getId()).get();
        assertNotNull(retrievedContentItem);
        assertEquals(savedContentItem.getId(), retrievedContentItem.getId());

        assertAll(() -> {
            assertEquals(application, retrievedContentItem.getApplication());
            assertEquals(resourceMap, retrievedContentItem.getResourceMap());
        });
    }

    @Test
    void givenContentItemWithNullFieldInResourceMap_whenSaveAndFlush_thenThrowDataIntegrityViolationException() {
        // Given
        ResourceMap resourceMap = new ResourceMap();
        resourceMap.setResourceValue("resourceValue");
        ContentItem contentItem = new ContentItem();
        contentItem.setApplication(application);
        contentItem.setResourceMap(resourceMap);

        // When / Then
        DataAccessException exception = assertThrows(DataAccessException.class, () -> contentItemRepository.saveAndFlush(contentItem));
        assertInstanceOf(DataIntegrityViolationException.class, exception);
        assertInstanceOf(ConstraintViolationException.class, exception.getCause());
    }

    @Test
    void givenContentItemAssociatedWithPageContent_whenSaveAndFlush_thenPersistWithPageContentReference() {
        // Given
        PageContent pageContent = new PageContent();
        pageContent.setApplication(application);
        pageContent.setPageName("Test Page");
        entityManager.persistAndFlush(pageContent);

        ResourceMap resourceMap = new ResourceMap();
        resourceMap.setResourceKey("resourceKey");
        resourceMap.setResourceValue("resourceValue");
        ContentItem contentItem = new ContentItem();
        contentItem.setApplication(application);
        contentItem.setResourceMap(resourceMap);
        contentItem.setPage(pageContent);

        // When
        ContentItem savedContentItem = contentItemRepository.saveAndFlush(contentItem);

        // Then
        assertNotNull(savedContentItem);
        assertTrue(savedContentItem.getId() > 0);
        assertEquals(savedContentItem.getPage().getId(), pageContent.getId());
    }

    @Test
    void givenNullApplication_whenSaveAndFlush_thenThrowException() {
        // Given
        ResourceMap resourceMap = new ResourceMap();
        resourceMap.setResourceKey("resourceKey");
        resourceMap.setResourceValue("resourceValue");
        ContentItem contentItem = new ContentItem();
        contentItem.setApplication(null);
        contentItem.setResourceMap(resourceMap);

        // When / Then
        DataAccessException dataAccessException = assertThrows(DataAccessException.class, () -> contentItemRepository.saveAndFlush(contentItem));
        assertNotNull(dataAccessException);
        assertInstanceOf(DataIntegrityViolationException.class, dataAccessException);
    }

    @Test
    void givenNonExistentPageContent_whenSaveAndFlush_thenThrowException() {
        // Given
        PageContent nonExistentPageContent = new PageContent();
        nonExistentPageContent.setId(999L);

        ResourceMap resourceMap = new ResourceMap();
        resourceMap.setResourceKey("resourceKey");
        resourceMap.setResourceValue("resourceValue");
        ContentItem contentItem = new ContentItem();
        contentItem.setApplication(application);
        contentItem.setResourceMap(resourceMap);
        contentItem.setPage(nonExistentPageContent);

        // When / Then
        DataAccessException dataAccessException = assertThrows(DataAccessException.class, () -> contentItemRepository.saveAndFlush(contentItem));
        assertNotNull(dataAccessException);
        assertInstanceOf(DataIntegrityViolationException.class, dataAccessException);
    }

    @Test
    @Disabled("This test case cannot be tested because unique keys are not declared yet")
    void givenDuplicateResourceMapKeyForSameApplication_whenSaveAndFlush_thenThrowException() {
        //Given
        ResourceMap resourceMap1 = new ResourceMap();
        resourceMap1.setResourceKey("resourceKey");
        resourceMap1.setResourceValue("resourceValue1");

        ContentItem contentItem1 = new ContentItem();
        contentItem1.setApplication(application);
        contentItem1.setResourceMap(resourceMap1);
        entityManager.persistAndFlush(contentItem1);

        ResourceMap resourceMap2 = new ResourceMap();
        resourceMap2.setResourceKey("resourceKey");
        resourceMap2.setResourceValue("resourceValue2");

        ContentItem contentItem2 = new ContentItem();
        contentItem2.setApplication(application);
        contentItem2.setResourceMap(resourceMap2);

        // When / Then
        DataAccessException dataAccessException = assertThrows(DataAccessException.class, () -> contentItemRepository.saveAndFlush(contentItem2));
        assertNotNull(dataAccessException);
        assertInstanceOf(DataIntegrityViolationException.class, dataAccessException);
    }

    @Test
    void givenDeletedContentItem_whenSaveAndFlush_thenSoftDeleteIsRespected() {
        // Given
        ResourceMap resourceMap = new ResourceMap();
        resourceMap.setResourceKey("resourceKey");
        resourceMap.setResourceValue("resourceValue");
        ContentItem contentItem = new ContentItem();
        contentItem.setApplication(application);
        contentItem.setResourceMap(resourceMap);

        // When
        contentItemRepository.saveAndFlush(contentItem);
        contentItemRepository.delete(contentItem);

        // Then
        assertFalse(contentItemRepository.findById(contentItem.getId()).isPresent());
    }

    @Test
    void givenLargeResourceMapValue_whenSaveAndFlush_thenPersistSuccessfully() {
        // Given
        String largeValue = "a".repeat(254);
        ResourceMap resourceMap = new ResourceMap();
        resourceMap.setResourceKey("resourceKey");
        resourceMap.setResourceValue(largeValue);
        ContentItem contentItem = new ContentItem();
        contentItem.setApplication(application);
        contentItem.setResourceMap(resourceMap);

        // When
        ContentItem savedContentItem = contentItemRepository.saveAndFlush(contentItem);

        // Then
        assertNotNull(savedContentItem);
        assertEquals(largeValue, savedContentItem.getResourceMap().getResourceValue());
    }

    @Test
    void givenNullResourceMap_whenSaveAndFlush_thenThrowException() {
        // Given
        ContentItem contentItem = new ContentItem();
        contentItem.setApplication(application);
        contentItem.setResourceMap(null);

        // When / Then
        DataAccessException dataAccessException = assertThrows(DataAccessException.class, () -> contentItemRepository.saveAndFlush(contentItem));
        assertNotNull(dataAccessException);
        assertInstanceOf(DataIntegrityViolationException.class, dataAccessException);
    }

    @Test
    void givenExistingContentItem_whenUpdate_thenValuesAreUpdatedCorrectly() {
        // Given
        ResourceMap resourceMap = new ResourceMap();
        resourceMap.setResourceKey("resourceKey");
        resourceMap.setResourceValue("resourceValue");
        ContentItem contentItem = new ContentItem();
        contentItem.setApplication(application);
        contentItem.setResourceMap(resourceMap);
        entityManager.persistAndFlush(contentItem);

        // When
        ContentItem retrievedContentItem = contentItemRepository.findById(contentItem.getId()).get();
        retrievedContentItem.getResourceMap().setResourceValue("updatedValue");
        contentItemRepository.saveAndFlush(retrievedContentItem);
        ContentItem updatedContentItem = contentItemRepository.findById(contentItem.getId()).get();

        // Then
        assertEquals("updatedValue", updatedContentItem.getResourceMap().getResourceValue());
    }
}
