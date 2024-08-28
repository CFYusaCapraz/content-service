package com.cyberfreak.services.domain;

import com.cyberfreak.services.domain.base.AuditableEntity;
import com.cyberfreak.services.dto.PageContentDto;
import com.cyberfreak.services.mapper.MapperBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "page_content")
@DynamicUpdate
public class PageContent extends AuditableEntity<PageContent, PageContentDto> {

    @Column(name = "page_name", nullable = false)
    private String pageName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @OneToMany(mappedBy = "page")
    private Set<ContentItem> contentItems;

    @Override
    public PageContentDto toDto(MapperBase<PageContent, PageContentDto> entityMapper) {
        return entityMapper.toDto(this);
    }

    @Override
    public PageContent fromDto(PageContentDto referenceDTO, MapperBase<PageContent, PageContentDto> entityMapper) {
        return entityMapper.toEntity(referenceDTO);
    }
}