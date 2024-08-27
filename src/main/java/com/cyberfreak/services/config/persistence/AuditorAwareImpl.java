package com.cyberfreak.services.config.persistence;

import com.cyberfreak.services.api.context.UserContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public @NotNull Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(UserContext.getCurrentUserId());
    }
}
