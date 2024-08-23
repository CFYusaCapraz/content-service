package com.cyberfreak.service.domain.listeners;

import com.cyberfreak.service.domain.base.AuditableEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditableEntityListener {

    @PrePersist
    public void prePersist(AuditableEntity entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setCreationTime(now);
        entity.setModificationTime(now);
    }

    @PreUpdate
    public void preUpdate(AuditableEntity entity) {
        entity.setModificationTime(LocalDateTime.now());
    }

    @PreRemove
    public void preRemove(AuditableEntity entity) {
        entity.setDeletedTime(LocalDateTime.now());
    }
}
