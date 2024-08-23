package com.cyberfreak.service.domain;

import com.cyberfreak.service.domain.base.AuditableEntity;
import com.cyberfreak.service.domain.embeddable.ResourceMap;
import com.cyberfreak.service.dto.ContentItemDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@Table(name = "content_item")
@DynamicUpdate
public class ContentItem extends AuditableEntity<ContentItem, ContentItemDto> {

    @Embedded
    private ResourceMap resourceMap;

    @ManyToOne(optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @ManyToOne
    @JoinColumn(name = "page_content_id")
    private PageContent page;

    @Override
    public ContentItemDto toDTO() {
        return null;
    }

    @Override
    public ContentItem fromDTO(ContentItemDto referenceDTO) {
        return null;
    }
}