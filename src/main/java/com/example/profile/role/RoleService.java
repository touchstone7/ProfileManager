package com.example.profile.role;

import com.example.profile.shared.ConflictException;
import com.example.profile.shared.NotFoundException;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RoleResponse create(CreateRoleRequest request) {
        if (roleRepository.existsByNameIgnoreCase(request.name())) {
            throw new ConflictException("Role already exists: " + request.name());
        }

        Role role = new Role();
        role.setName(request.name());
        role.setDescription(request.description());
        role.setActive(request.active() == null || request.active());

        return RoleResponse.from(roleRepository.save(role));
    }

    @Transactional(readOnly = true)
    public RoleResponse getById(UUID id) {
        return RoleResponse.from(findRole(id));
    }

    @Transactional(readOnly = true)
    public RoleResponse getByName(String name) {
        return roleRepository.findByNameIgnoreCase(name)
                .map(RoleResponse::from)
                .orElseThrow(() -> new NotFoundException("Role not found for name: " + name));
    }

    @Transactional(readOnly = true)
    public Page<RoleResponse> list(Pageable pageable) {
        return roleRepository.findAll(pageable).map(RoleResponse::from);
    }

    @Transactional
    public RoleResponse update(UUID id, UpdateRoleRequest request) {
        Role role = findRole(id);

        if (request.name() != null && !request.name().equalsIgnoreCase(role.getName())) {
            if (roleRepository.existsByNameIgnoreCase(request.name())) {
                throw new ConflictException("Role already exists: " + request.name());
            }
            role.setName(request.name());
        }
        if (request.description() != null) {
            role.setDescription(request.description());
        }
        if (request.active() != null) {
            role.setActive(request.active());
        }

        return RoleResponse.from(role);
    }

    @Transactional
    public void delete(UUID id) {
        Role role = findRole(id);
        role.setActive(false);
    }

    private Role findRole(UUID id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Role not found: " + id));
    }
}
