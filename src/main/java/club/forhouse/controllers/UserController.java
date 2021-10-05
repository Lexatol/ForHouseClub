package club.forhouse.controllers;

import club.forhouse.dto.UserDto;
import club.forhouse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userServices;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userServices.findById(id);
    }

    @GetMapping("/profile/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userServices.findByEmail(email);
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userServices.findAll();
    }
}
