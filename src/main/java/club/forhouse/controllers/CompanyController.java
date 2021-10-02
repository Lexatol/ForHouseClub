package club.forhouse.controllers;

import club.forhouse.dto.CompanyDto;
import club.forhouse.entities.Company;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.services.CompanyServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/companies")
public class CompanyController {
    private final CompanyServices companyServices;

    @GetMapping
    public List<CompanyDto> findAll() {
        return companyServices.findAll();
    }


    @GetMapping("/{id}")
    public CompanyDto findLkContractorById(@PathVariable Long id) {
        Company company = companyServices.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find LK with id: " + id));
        return new CompanyDto(company);
    }
}
