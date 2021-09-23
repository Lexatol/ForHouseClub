package club.forhouse.services;

import club.forhouse.dto.CompanyDto;
import club.forhouse.entities.Company;
import club.forhouse.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServices {
    public final CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    public CompanyDto findByName(String companyName) {
        return new CompanyDto(companyRepository.findByName(companyName));
    }
}
