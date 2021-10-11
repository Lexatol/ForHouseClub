package club.forhouse.controllers;


import club.forhouse.dto.ProfileContractorDto;
import club.forhouse.entities.ProfileCompany;
import club.forhouse.entities.User;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.services.ProfileCompanyService;
import club.forhouse.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/profile_companies")
public class ProfileContractorController {
    private final ProfileCompanyService profileCompanyService;

    @GetMapping
    public List<ProfileContractorDto> findAll() {
        return profileCompanyService.findAll();
    }


    @GetMapping("/{id}")
    public ProfileContractorDto findProfileContractorById(@PathVariable Long id) {
        return profileCompanyService.findById(id);
    }

    @GetMapping("/company_info")
    public ProfileContractorDto findProfileContractorByGeneralManager(@RequestParam String email) {
        return profileCompanyService.findCompanyByGeneralManagerEmail(email);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<?> updateProfileContractor(Principal principal, @RequestBody ProfileContractorDto profileContractorDto) {
        //User currentUser = userService.findByUserEmail(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to find current user"));
        //ProfileCompany p = profileCompanyService.findCompanyByEmailForSave(currentUser.getUserEmail());

        //System.out.println(profileContractorDto.getCompany().getGeneralManager().getUserEmail());
        //System.out.println(profileContractorDto.getCompany().getCompanyName());
        profileCompanyService.saveOrUpdate(profileContractorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
