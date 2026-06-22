package com.example.profile.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateRoleRequest(
        @NotBlank @Size(max = 80) String name,
        @Size(max = 255) String description,
        Boolean active
) {
}
