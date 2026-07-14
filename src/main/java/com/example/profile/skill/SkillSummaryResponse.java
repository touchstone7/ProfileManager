package com.example.profile.skill;

import java.util.UUID;

public record SkillSummaryResponse(
        UUID id,
        String name
) {
    public static SkillSummaryResponse from(Skill skill) {
        return new SkillSummaryResponse(skill.getId(), skill.getName());
    }
}
