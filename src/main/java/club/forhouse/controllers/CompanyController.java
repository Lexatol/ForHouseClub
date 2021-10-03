package club.forhouse.controllers;

import club.forhouse.dto.CompanyDto;
import club.forhouse.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDto> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public CompanyDto findCompanyById(@PathVariable Long id) {
        return companyService.findById(id);
    }
}
