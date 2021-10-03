package club.forhouse.services;

import club.forhouse.dto.SystemUserDto;
import club.forhouse.dto.UserDto;
import club.forhouse.entities.User;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<UserDto> findAll() {
        List<UserDto> listUsers = userRepository.findAll()
                .stream().map(UserDto::new)
                .collect(Collectors.toList());
        return listUsers;
    }

    public UserDto findByUsername(String name) {
        User user = userRepository.findUserByUserName(name).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find user with id: " + name));
        return new UserDto(user);
    }

    public Optional<User> findByUsernameForRegistration(String name) {
        return userRepository.findUserByUserName(name);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find user with id: " + id));
        return new UserDto(user);
    }

    public UserDto findByEmail(String email) {
        User user = userRepository.findUserByUserEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find user with name: " + email));
        return new UserDto(user);
    }

    public Optional<User> findUserByUserEmailAndUserPassword(String email, String password) {
        return userRepository.findUserByUserEmailAndUserPassword(email, password);
    }

    public User saveUserFromDto(SystemUserDto systemUserDto) {
        User user = new User();
        user.setUserName(systemUserDto.getName());
        user.setUserEmail(systemUserDto.getEmail());
        user.setUserPassword(systemUserDto.getPassword());
        user.setUserRoles(Arrays.asList(roleService.getUserRole()));
        return save(user);
    }
}
