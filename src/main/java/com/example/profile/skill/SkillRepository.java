package com.example.profile.skill;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, UUID> {

    List<Skill> findByProfileId(UUID profileId);

    boolean existsByProfileIdAndNameIgnoreCase(UUID profileId, String name);

    boolean existsByProfileIdAndNameIgnoreCaseAndIdNot(UUID profileId, String name, UUID id);
}
