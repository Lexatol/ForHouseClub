package club.forhouse.controllers;


import club.forhouse.dto.CompanyDto;
import club.forhouse.dto.ProfileContractorDto;
import club.forhouse.entities.ProfileCompanies;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.services.ProfileContractorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        ProfileCompanies pfC = profileContractorServices.findCompanyByGeneralManagerEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find LK with id: " + email));
        return new ProfileContractorDto(pfC);
    }
}
