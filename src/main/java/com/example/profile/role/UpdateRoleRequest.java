package com.example.profile.role;

import jakarta.validation.constraints.Size;

public record UpdateRoleRequest(
        @Size(max = 80) String name,
        @Size(max = 255) String description,
        Boolean active
) {
}
