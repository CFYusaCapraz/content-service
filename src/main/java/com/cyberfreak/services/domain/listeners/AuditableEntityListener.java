package com.cyberfreak.services.domain.listeners;

import com.cyberfreak.services.domain.base.AuditableEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class AuditableEntityListener {

    @PrePersist
    public void prePersist(@NotNull AuditableEntity entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setCreationTime(now);
        entity.setModificationTime(now);
    }

    @PreUpdate
    public void preUpdate(@NotNull AuditableEntity entity) {
        entity.setModificationTime(LocalDateTime.now());
    }

    @PreRemove
    public void preRemove(@NotNull AuditableEntity entity) {
        entity.setDeletionTime(LocalDateTime.now());
    }
}
