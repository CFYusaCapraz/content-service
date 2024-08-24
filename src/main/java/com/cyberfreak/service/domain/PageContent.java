package com.cyberfreak.service.domain;

import com.cyberfreak.service.domain.base.AuditableEntity;
import com.cyberfreak.service.dto.PageContentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "page_content")
public class PageContent extends AuditableEntity<PageContent, PageContentDto> {

    @Column(name = "page_name", nullable = false)
    private String pageName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @OneToMany(mappedBy = "page")
    private Set<ContentItem> contentItems;

    @Override
    public PageContentDto toDto() {
        return null;
    }

    @Override
    public PageContent fromDto(PageContentDto referenceDTO) {
        return null;
    }
}