package club.forhouse.services;

import club.forhouse.dto.CompanyDto;
import club.forhouse.entities.Company;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {
    public final CompanyRepository companyRepository;

    public List<CompanyDto> findAll() {
        return companyRepository.findAll().stream().map(CompanyDto::new).collect(Collectors.toList());
    }

    public CompanyDto findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find LK with id: " + id));
        return new CompanyDto(company);
    }
}
