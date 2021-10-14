package club.forhouse.services.profiles;

import club.forhouse.dto.registration.SystemUserDto;
import club.forhouse.dto.profiles.UserDto;
import club.forhouse.entities.profiles.Role;
import club.forhouse.entities.profiles.User;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.UserMapper;
import club.forhouse.repositories.profiles.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserMapper userMapper;

    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<UserDto> findAll() {
        return userMapper.toListDto(userRepository.findAll());
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

    public Optional<User> findOptionalByEmail(String email) {
        return userRepository.findUserByUserEmail(email);
    }

    public User findByUserName(String name) {
        User user = userRepository.findUserByUserName(name)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find user with name: " + name));
        return user;
    }

    public Optional<User> findByUserEmail(String mail) {
        return userRepository.findUserByUserEmail(mail);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = findByUserEmail(mail).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", mail)));
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getUserPassword(), mapRolesToAuthorities(user.getUserRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    public User saveUserFromDto(SystemUserDto systemUserDto) {
        User user = new User();
        user.setUserName(systemUserDto.getUserName());
        user.setUserEmail(systemUserDto.getUserEmail());
        user.setUserPassword(passwordEncoder.encode(systemUserDto.getUserPassword()));
        user.setUserRoles(Arrays.asList(roleService.getUserRole()));
        return save(user);
    }

    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }
}
