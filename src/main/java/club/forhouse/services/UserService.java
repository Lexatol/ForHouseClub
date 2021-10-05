package club.forhouse.services;

import club.forhouse.dto.SystemUserDto;
import club.forhouse.dto.UserDto;
import club.forhouse.entities.User;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.UserMapper;
import club.forhouse.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<UserDto> findAll() {
        return userMapper.toListDto(userRepository.findAll());
    }

    public UserDto findByUsername(String name) {
        User user = userRepository.findUserByUserName(name).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find user with id: " + name));
        return userMapper.toDto(user);
    }

    public Optional<User> findByUsernameForRegistration(String name) {
        return userRepository.findUserByUserName(name);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find user with id: " + id));
        return userMapper.toDto(user);
    }

    public UserDto findByEmail(String email) {
        User user = userRepository.findUserByUserEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find user with name: " + email));
        return userMapper.toDto(user);
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
