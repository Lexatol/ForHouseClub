package club.forhouse.services;

import club.forhouse.entities.User;
import club.forhouse.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByUserEmailAndUserPassword(String email, String password) {
        return userRepository.findUserByUserEmailAndUserPassword(email, password);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByUserEmail(email);
    }
}
