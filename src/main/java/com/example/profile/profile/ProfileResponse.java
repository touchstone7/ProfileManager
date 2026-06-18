package com.example.profile.profile;

import java.time.Instant;
import java.time.LocalDate;
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
        Instant createdAt,
        Instant updatedAt
) {
    static ProfileResponse from(Profile profile) {
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
                profile.getCreatedAt(),
                profile.getUpdatedAt()
        );
    }
}
