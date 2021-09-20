package ForHouseClub.services;

import ForHouseClub.entities.Company;
import ForHouseClub.entities.LkContractor;
import ForHouseClub.repositories.LkContractorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LkContractorService {
    private final LkContractorRepository lkContractorRepository;
    private final CompanyServices companyServices;

    public List<LkContractor> findAll() {
        return lkContractorRepository.findAll();
    }

    public Optional<LkContractor> findById(Long id) {
        return lkContractorRepository.findById(id);
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
