package club.forhouse.controllers;

import club.forhouse.dto.SystemUserDto;
import club.forhouse.dto.UserDto;
import club.forhouse.entities.User;
import club.forhouse.exceptions.MarketError;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userServices;
    private final PasswordEncoder passwordEncoder;

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

    /*@GetMapping(produces = "application/json")
    public SystemUserDto getCurrentProfile(Principal principal) {
        User user = userServices.findOptionalByEmail(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find profile for current user"));
        return new SystemUserDto(user);
    }*/

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> getCurrentProfile(Principal principal, @RequestBody SystemUserDto systemUserDto) {

        User currentUser = userServices.findByUserEmail(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find current user"));
        if (systemUserDto.getConfirmationPassword() == null || !passwordEncoder.matches(systemUserDto.getConfirmationPassword(), currentUser.getUserPassword())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Incorrect password"), HttpStatus.BAD_REQUEST);
        }

        currentUser.setUserName(systemUserDto.getUserName());
        currentUser.setUserEmail(systemUserDto.getUserEmail());
        currentUser.setUserPhone(systemUserDto.getUserPhone());
        currentUser.setUserPassword(passwordEncoder.encode(systemUserDto.getUserPassword()));

        userServices.saveOrUpdate(currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
