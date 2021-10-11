package club.forhouse.controllers;

import club.forhouse.dto.SystemUserDto;
import club.forhouse.exceptions.RegistrationError;
import club.forhouse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody @Validated SystemUserDto systemUser, BindingResult bindingResult) {
        if (userService.findByUsernameForRegistration(systemUser.getName()).isPresent()) {
            return new ResponseEntity<>(new RegistrationError("such user already exists"), HttpStatus.BAD_REQUEST);
        }
        if (!systemUser.getPassword().equals(systemUser.getConfirmationPassword())) {
            return new ResponseEntity<>(new RegistrationError("password mismatch"), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new RegistrationError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
        }

        userService.saveUserFromDto(systemUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
