package club.forhouse.services;

import club.forhouse.entities.Company;
import club.forhouse.entities.LkCompany;
import club.forhouse.repositories.LkCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LkCompanyService {
    private final LkCompanyRepository lkCompanyRepository;
    private final CompanyServices companyServices;

    public List<LkCompany> findAll() {
        return lkCompanyRepository.findAll();
    }

    public Optional<LkCompany> findById(Long id) {
        return lkCompanyRepository.findById(id);
    }

    //TODO найти список всех подрядчиков
    public List<Company> companiesCustomer() {
        return null;
    }

    //TODO найти список всех поставщиков
    public List<Company> companiesProvider() {
        return null;
    }

}
