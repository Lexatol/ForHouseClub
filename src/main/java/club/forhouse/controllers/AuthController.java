package club.forhouse.controllers;

import club.forhouse.dto.LoginForm;
import club.forhouse.entities.User;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody LoginForm loginForm) {

        User currentUser = userService
                .findUserByUserEmailAndUserPassword(loginForm.getEmail(), loginForm.getPassword())
                .stream().findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Unable to find user with email or password: " + loginForm.getEmail()));

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
