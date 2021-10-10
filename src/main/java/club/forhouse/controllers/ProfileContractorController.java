package club.forhouse.controllers;


import club.forhouse.dto.ProfileContractorDto;
import club.forhouse.services.ProfileCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/save")
    public ResponseEntity<?> saveOrUpdateProfile(@RequestBody @Validated ProfileContractorDto profileContractorDto) {
        profileCompanyService.saveOrUpdate(profileContractorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
