package club.forhouse.controllers;

import club.forhouse.dto.UserDto;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserServices userServices;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userServices.findById(id)
                .stream().map(UserDto::new).findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find user with id: " + id));
    }

    @GetMapping("/profile/{email}")
    public UserDto getUserById(@PathVariable String email) {
        return userServices.findByEmail(email)
                .stream().map(UserDto::new).findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find user with name: " + email));
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userServices.findAll()
                .stream().map(UserDto::new)
                .collect(Collectors.toList());
    }
}
