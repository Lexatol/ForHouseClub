package club.forhouse.controllers;

import club.forhouse.dto.LkCompanyDto;
import club.forhouse.entities.LkCompany;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.services.LkCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/lk_companies")
public class LkCompanyController {
    private final LkCompanyService lkCompanyService;

    @GetMapping
    public List<LkCompanyDto> findAll() {
        return lkCompanyService.findAll()
                .stream().map(LkCompanyDto::new).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public LkCompanyDto findLkContractorById(@PathVariable Long id) {
        LkCompany lkC = lkCompanyService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find LK with id: " + id));
        return new LkCompanyDto(lkC);
    }


}
