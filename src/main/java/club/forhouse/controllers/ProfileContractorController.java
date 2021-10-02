package club.forhouse.controllers;


import club.forhouse.dto.CompanyDto;
import club.forhouse.dto.ProfileContractorDto;
import club.forhouse.entities.Company;
import club.forhouse.entities.ProfileCompanies;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.services.ProfileContractorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/profile_companies")
public class ProfileContractorController {
    private final ProfileContractorServices profileContractorServices;

    @GetMapping
    public List<ProfileContractorDto> findAll() {
        return profileContractorServices.findAll()
                .stream().map(ProfileContractorDto::new).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ProfileContractorDto findProfileContractorById(@PathVariable Long id) {
        ProfileCompanies pfC = profileContractorServices.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find profile with id: " + id));
        return new ProfileContractorDto(pfC);
    }

    @GetMapping("/company_info")
    public ProfileContractorDto findProfileContractorByGeneralManager(@RequestParam String email) {
        ProfileCompanies pfC = profileContractorServices.findCompanyByGeneralManagerEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find LK with id: " + email));
        return new ProfileContractorDto(pfC);
    }
}
