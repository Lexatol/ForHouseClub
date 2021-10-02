package club.forhouse.services;

import club.forhouse.dto.CompanyDto;
import club.forhouse.entities.Company;
import club.forhouse.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServices {
    public final CompanyRepository companyRepository;

    public List<CompanyDto> findAll() {
        return companyRepository.findAll().stream().map(CompanyDto::new).collect(Collectors.toList());
    }

    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    public Optional<Company> findCompanyByGeneralManagerEmail(String manager) {
        return companyRepository.findCompanyByGeneralManagerEmail(manager);
    }
}
