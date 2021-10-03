package club.forhouse.services;

import club.forhouse.entities.Role;
import club.forhouse.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role getUserRole() {
        return roleRepository.findRoleByRoleName("ROLE_USER");
    }

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}
