package com.example.profile.profile;

import com.example.profile.skill.CreateSkillRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UpdateProfileRequest(
        @Email @Size(max = 120) String email,
        @Size(max = 80) String firstName,
        @Size(max = 80) String lastName,
        @Size(max = 30) String phoneNumber,
        @Size(max = 80) String displayName,
        @Size(max = 80) String jobTitle,
        @Size(max = 80) String department,
        @Size(max = 80) String location,
        @Past LocalDate dateOfBirth,
        @Size(max = 500) String bio,
        @Size(max = 120) String avatarUrl,
        @Size(max = 40) String preferredLanguage,
        @Size(max = 40) String timeZone,
        ProfileStatus status,
        List<UUID> roleIds,
        List<@Valid CreateSkillRequest> skills
) {
}
