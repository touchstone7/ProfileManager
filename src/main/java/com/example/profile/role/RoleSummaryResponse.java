package com.example.profile.role;

import java.util.UUID;

public record RoleSummaryResponse(
        UUID id,
        String name
) {
    public static RoleSummaryResponse from(Role role) {
        return new RoleSummaryResponse(role.getId(), role.getName());
    }
}
