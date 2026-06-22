package com.example.profile.role;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.profile.shared.ConflictException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    void createsRole() {
        RoleResponse response = roleService.create(new CreateRoleRequest(
                "AUDITOR",
                "Can review profile audit trails.",
                true
        ));

        assertThat(response.id()).isNotNull();
        assertThat(response.name()).isEqualTo("AUDITOR");
        assertThat(response.active()).isTrue();
    }

    @Test
    void rejectsDuplicateRoleName() {
        CreateRoleRequest request = new CreateRoleRequest(
                "USER",
                "Duplicate role.",
                true
        );

        assertThatThrownBy(() -> roleService.create(request))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Role already exists");
    }
}
