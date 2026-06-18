package com.example.profile.profile;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> create(@Valid @RequestBody CreateProfileRequest request) {
        ProfileResponse response = profileService.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ProfileResponse getById(@PathVariable UUID id) {
        return profileService.getById(id);
    }

    @GetMapping("/by-username/{username}")
    public ProfileResponse getByUsername(@PathVariable String username) {
        return profileService.getByUsername(username);
    }

    @GetMapping
    public Page<ProfileResponse> list(
            @RequestParam(required = false) ProfileStatus status,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable
    ) {
        return profileService.list(status, pageable);
    }

    @PatchMapping("/{id}")
    public ProfileResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProfileRequest request
    ) {
        return profileService.update(id, request);
    }

    @PatchMapping("/{id}/deactivate")
    public ProfileResponse deactivate(@PathVariable UUID id) {
        return profileService.deactivate(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        profileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
