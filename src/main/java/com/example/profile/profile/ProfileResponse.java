package com.example.profile.profile;

import com.example.profile.role.RoleSummaryResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ProfileResponse(
        UUID id,
        String username,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        String displayName,
        String jobTitle,
        String department,
        String location,
        LocalDate dateOfBirth,
        String bio,
        String avatarUrl,
        String preferredLanguage,
        String timeZone,
        ProfileStatus status,
        List<RoleSummaryResponse> roles,
        Instant createdAt,
        Instant updatedAt
) {
    static ProfileResponse from(Profile profile) {
        List<RoleSummaryResponse> roles = profile.getRoles()
                .stream()
                .map(RoleSummaryResponse::from)
                .toList();

        return new ProfileResponse(
                profile.getId(),
                profile.getUsername(),
                profile.getEmail(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getPhoneNumber(),
                profile.getDisplayName(),
                profile.getJobTitle(),
                profile.getDepartment(),
                profile.getLocation(),
                profile.getDateOfBirth(),
                profile.getBio(),
                profile.getAvatarUrl(),
                profile.getPreferredLanguage(),
                profile.getTimeZone(),
                profile.getStatus(),
                roles,
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}
