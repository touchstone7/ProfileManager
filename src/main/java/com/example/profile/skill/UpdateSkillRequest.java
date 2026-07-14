package com.example.profile.skill;

import jakarta.validation.constraints.Size;

public record UpdateSkillRequest(
        @Size(max = 80) String name,
        @Size(max = 255) String description
) {
}
