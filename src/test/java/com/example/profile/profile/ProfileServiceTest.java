package com.example.profile.profile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.profile.shared.ConflictException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    @Test
    void createsProfileWithActiveStatus() {
        CreateProfileRequest request = new CreateProfileRequest(
                "rahul.mehta",
                "rahul.mehta@example.com",
                "Rahul",
                "Mehta",
                "+91-9000000001",
                "Rahul",
                "Engineering Manager",
                "Engineering",
                "Hyderabad",
                null,
                "Leads platform teams.",
                null,
                "en-IN",
                "Asia/Kolkata",
                null,
                null
        );

        ProfileResponse response = profileService.create(request);

        assertThat(response.id()).isNotNull();
        assertThat(response.status()).isEqualTo(ProfileStatus.ACTIVE);
        assertThat(response.username()).isEqualTo("rahul.mehta");
        assertThat(response.roles()).isEmpty();
        assertThat(response.skills()).isEmpty();
    }

    @Test
    void rejectsDuplicateEmail() {
        CreateProfileRequest request = new CreateProfileRequest(
                "same.email",
                "ananya.rao@example.com",
                "Same",
                "Email",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        assertThatThrownBy(() -> profileService.create(request))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Email already exists");
    }
}
