package com.cyberfreak.service.domain.listeners;

import com.cyberfreak.service.domain.base.BaseEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import org.jetbrains.annotations.NotNull;

public class BaseEntityListener {

    @PrePersist
    public void prePersist(@NotNull BaseEntity entity) {
        entity.setVersion(0);
    }

    @PreRemove
    public void preRemove(@NotNull BaseEntity entity) {
        entity.setIsDeleted(true);
    }
}
