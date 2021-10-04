package club.forhouse.services;

import club.forhouse.dto.CompanyDto;
import club.forhouse.entities.Company;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.CompanyMapper;
import club.forhouse.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    public final CompanyRepository companyRepository;
    public final CompanyMapper companyMapper;

    public List<CompanyDto> findAll() {
        return companyMapper.toListDto(companyRepository.findAll());
    }

    public CompanyDto findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Company with id: " + id));
        return companyMapper.toDto(company);
    }
}
