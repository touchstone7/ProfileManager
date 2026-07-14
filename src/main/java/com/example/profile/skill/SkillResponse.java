package com.example.profile.skill;

import java.time.Instant;
import java.util.UUID;

public record SkillResponse(
        UUID id,
        UUID profileId,
        String name,
        String description,
        Instant createdAt,
        Instant updatedAt
) {
    public static SkillResponse from(Skill skill) {
        return new SkillResponse(
                skill.getId(),
                skill.getProfile().getId(),
                skill.getName(),
                skill.getDescription(),
                skill.getCreatedAt(),
                skill.getUpdatedAt()
        );
    }
}
