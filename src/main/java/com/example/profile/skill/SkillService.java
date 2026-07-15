package com.example.profile.skill;

import com.example.profile.profile.Profile;
import com.example.profile.profile.ProfileRepository;
import com.example.profile.shared.ConflictException;
import com.example.profile.shared.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final ProfileRepository profileRepository;

    public SkillService(SkillRepository skillRepository, ProfileRepository profileRepository) {
        this.skillRepository = skillRepository;
        this.profileRepository = profileRepository;
    }
    // looking for CRUD operations this is a POST request to create a new skill
    @Transactional
    public SkillResponse create(UUID profileId, CreateSkillRequest request) {
        Profile profile = findProfile(profileId);

        if (skillRepository.existsByProfileIdAndNameIgnoreCase(profileId, request.name())) {
            throw new ConflictException("Skill already exists for profile: " + request.name());
        }

        Skill skill = new Skill();
        skill.setName(request.name());
        skill.setDescription(request.description());
        skill.setProfile(profile);

        return SkillResponse.from(skillRepository.save(skill));
    }
    // looking for CRUD operations this is a GET request to get a skill by id
    @Transactional(readOnly = true)
    public SkillResponse getById(UUID id) {
        return SkillResponse.from(findSkill(id));
    }

    @Transactional(readOnly = true)
    public List<SkillResponse> listByProfileId(UUID profileId) {
        findProfile(profileId);
        return skillRepository.findByProfileId(profileId)
                .stream()
                .map(SkillResponse::from)
                .toList();
    }
    // looking for CRUD operations this is a GET request to get all skills by profile id
    @Transactional
    public SkillResponse update(UUID id, UpdateSkillRequest request) {
        Skill skill = findSkill(id);

        if (request.name() != null && !request.name().equalsIgnoreCase(skill.getName())) {
            UUID profileId = skill.getProfile().getId();
            if (skillRepository.existsByProfileIdAndNameIgnoreCaseAndIdNot(profileId, request.name(), id)) {
                throw new ConflictException("Skill already exists for profile: " + request.name());
            }
            skill.setName(request.name());
        }
        if (request.description() != null) {
            skill.setDescription(request.description());
        }

        return SkillResponse.from(skill);
    }
    // looking for CRUD operations this is a DELETE request to delete a skill
    @Transactional
    public void delete(UUID id) {
        Skill skill = findSkill(id);
        skillRepository.delete(skill);
    }

    private Profile findProfile(UUID profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Profile not found: " + profileId));
    }

    private Skill findSkill(UUID id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Skill not found: " + id));
    }
}
