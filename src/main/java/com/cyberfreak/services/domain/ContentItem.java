package com.cyberfreak.services.domain;

import com.cyberfreak.services.api.context.CycleAvoidingMappingContext;
import com.cyberfreak.services.domain.base.AuditableEntity;
import com.cyberfreak.services.domain.embeddable.ResourceMap;
import com.cyberfreak.services.dto.ContentItemDto;
import com.cyberfreak.services.mapper.MapperBase;
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
    public ContentItemDto toDto(MapperBase<ContentItem, ContentItemDto> entityMapper) {
        return entityMapper.toDto(this, new CycleAvoidingMappingContext());
    }

    @Override
    public ContentItem fromDto(ContentItemDto referenceDTO, MapperBase<ContentItem, ContentItemDto> entityMapper) {
        return entityMapper.toEntity(referenceDTO, new CycleAvoidingMappingContext());
    }
}