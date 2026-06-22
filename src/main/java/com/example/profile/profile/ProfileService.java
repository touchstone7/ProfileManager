package com.example.profile.profile;

import com.example.profile.role.Role;
import com.example.profile.role.RoleRepository;
import com.example.profile.shared.ConflictException;
import com.example.profile.shared.NotFoundException;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final RoleRepository roleRepository;

    public ProfileService(ProfileRepository profileRepository, RoleRepository roleRepository) {
        this.profileRepository = profileRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public ProfileResponse create(CreateProfileRequest request) {
        if (profileRepository.existsByUsernameIgnoreCase(request.username())) {
            throw new ConflictException("Username already exists: " + request.username());
        }
        if (profileRepository.existsByEmailIgnoreCase(request.email())) {
            throw new ConflictException("Email already exists: " + request.email());
        }

        Profile profile = new Profile();
        profile.setUsername(request.username());
        profile.setEmail(request.email());
        profile.setFirstName(request.firstName());
        profile.setLastName(request.lastName());
        profile.setPhoneNumber(request.phoneNumber());
        profile.setDisplayName(request.displayName());
        profile.setJobTitle(request.jobTitle());
        profile.setDepartment(request.department());
        profile.setLocation(request.location());
        profile.setDateOfBirth(request.dateOfBirth());
        profile.setBio(request.bio());
        profile.setAvatarUrl(request.avatarUrl());
        profile.setPreferredLanguage(request.preferredLanguage());
        profile.setTimeZone(request.timeZone());
        profile.setStatus(ProfileStatus.ACTIVE);
        profile.setRoles(resolveRoles(request.roleIds()));

        return ProfileResponse.from(profileRepository.save(profile));
    }

    @Transactional(readOnly = true)
    public ProfileResponse getById(UUID id) {
        return ProfileResponse.from(findProfile(id));
    }

    @Transactional(readOnly = true)
    public ProfileResponse getByUsername(String username) {
        return profileRepository.findByUsernameIgnoreCase(username)
                .map(ProfileResponse::from)
                .orElseThrow(() -> new NotFoundException("Profile not found for username: " + username));
    }

    @Transactional(readOnly = true)
    public Page<ProfileResponse> list(ProfileStatus status, Pageable pageable) {
        Page<Profile> profiles = status == null
                ? profileRepository.findAll(pageable)
                : profileRepository.findByStatus(status, pageable);
        return profiles.map(ProfileResponse::from);
    }

    @Transactional
    public ProfileResponse update(UUID id, UpdateProfileRequest request) {
        Profile profile = findProfile(id);

        if (request.email() != null && !request.email().equalsIgnoreCase(profile.getEmail())) {
            if (profileRepository.existsByEmailIgnoreCase(request.email())) {
                throw new ConflictException("Email already exists: " + request.email());
            }
            profile.setEmail(request.email());
        }
        if (request.firstName() != null) {
            profile.setFirstName(request.firstName());
        }
        if (request.lastName() != null) {
            profile.setLastName(request.lastName());
        }
        if (request.phoneNumber() != null) {
            profile.setPhoneNumber(request.phoneNumber());
        }
        if (request.displayName() != null) {
            profile.setDisplayName(request.displayName());
        }
        if (request.jobTitle() != null) {
            profile.setJobTitle(request.jobTitle());
        }
        if (request.department() != null) {
            profile.setDepartment(request.department());
        }
        if (request.location() != null) {
            profile.setLocation(request.location());
        }
        if (request.dateOfBirth() != null) {
            profile.setDateOfBirth(request.dateOfBirth());
        }
        if (request.bio() != null) {
            profile.setBio(request.bio());
        }
        if (request.avatarUrl() != null) {
            profile.setAvatarUrl(request.avatarUrl());
        }
        if (request.preferredLanguage() != null) {
            profile.setPreferredLanguage(request.preferredLanguage());
        }
        if (request.timeZone() != null) {
            profile.setTimeZone(request.timeZone());
        }
        if (request.status() != null) {
            profile.setStatus(request.status());
        }
        if (request.roleIds() != null) {
            profile.setRoles(resolveRoles(request.roleIds()));
        }

        return ProfileResponse.from(profile);
    }

    @Transactional
    public ProfileResponse deactivate(UUID id) {
        Profile profile = findProfile(id);
        profile.setStatus(ProfileStatus.INACTIVE);
        return ProfileResponse.from(profile);
    }

    @Transactional
    public void delete(UUID id) {
        Profile profile = findProfile(id);
        profile.setStatus(ProfileStatus.DELETED);
    }

    private Profile findProfile(UUID id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profile not found: " + id));
    }

    private List<Role> resolveRoles(List<UUID> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            return List.of();
        }

        List<Role> roles = roleRepository.findByIdIn(roleIds);
        if (roles.size() != roleIds.stream().distinct().count()) {
            throw new NotFoundException("One or more roles were not found");
        }
        return roles;
    }
}
