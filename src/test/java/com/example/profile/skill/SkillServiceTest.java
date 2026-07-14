package com.example.profile.skill;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.profile.shared.ConflictException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SkillServiceTest {

    private static final UUID SEEDED_PROFILE_ID = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");

    @Autowired
    private SkillService skillService;

    @Test
    void createsSkillForProfile() {
        SkillResponse response = skillService.create(
                SEEDED_PROFILE_ID,
                new CreateSkillRequest("Roadmapping", "Builds quarterly product roadmaps.")
        );

        assertThat(response.id()).isNotNull();
        assertThat(response.profileId()).isEqualTo(SEEDED_PROFILE_ID);
        assertThat(response.name()).isEqualTo("Roadmapping");
    }

    @Test
    void rejectsDuplicateSkillNameForProfile() {
        CreateSkillRequest request = new CreateSkillRequest(
                "Product Strategy",
                "Duplicate skill for the same profile."
        );

        assertThatThrownBy(() -> skillService.create(SEEDED_PROFILE_ID, request))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Skill already exists for profile");
    }
}
