package com.example.profile.role;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    boolean existsByNameIgnoreCase(String name);

    Optional<Role> findByNameIgnoreCase(String name);

    List<Role> findByIdIn(Collection<UUID> ids);
}
