package club.forhouse.services.profiles;

import club.forhouse.dto.profiles.RoleDto;
import club.forhouse.entities.profiles.Role;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.RoleMapper;
import club.forhouse.repositories.profiles.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<RoleDto> findAll() {
        return roleMapper.toListDto(roleRepository.findAll());
    }

    public Role getUserRole() {
        return roleRepository.findRoleByRoleName("ROLE_USER");
    }

    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find role with id: " + id));
        return roleMapper.toDto(role);
    }
}
