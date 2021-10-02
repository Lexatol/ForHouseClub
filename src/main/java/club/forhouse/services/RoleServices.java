package club.forhouse.services;

import club.forhouse.entities.Role;
import club.forhouse.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServices {
    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    public Role getUserRole() {
        return roleRepository.findRoleByRoleName("ROLE_USER");
    }
}
