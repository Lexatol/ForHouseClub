package club.forhouse.controllers.registrations;

import club.forhouse.dto.profiles.CompanyDto;
import club.forhouse.dto.registration.SystemUserDto;
import club.forhouse.exceptions.RegistrationError;
import club.forhouse.services.profiles.CompanyService;
import club.forhouse.services.profiles.ProfileCompanyService;
import club.forhouse.services.profiles.UserService;
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
    private final CompanyService companyService;
    private final ProfileCompanyService profileCompanyService;

    @PostMapping("/register")
    public ResponseEntity<?> registration(@RequestBody @Validated SystemUserDto systemUser, BindingResult bindingResult) {
        if (userService.findByUsernameForRegistration(systemUser.getUserName()).isPresent()) {
            return new ResponseEntity<>(new RegistrationError("such user already exists"), HttpStatus.BAD_REQUEST);
        }
        if (!systemUser.getUserPassword().equals(systemUser.getConfirmationPassword())) {
            return new ResponseEntity<>(new RegistrationError("password mismatch"), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new RegistrationError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
        }

        userService.saveUserFromDto(systemUser);
        CompanyDto newCompany = companyService.saveCompanyFromName(systemUser.getCompanyName(), systemUser.getUserName());
        profileCompanyService.saveProfileFromName(newCompany.getCompanyName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
