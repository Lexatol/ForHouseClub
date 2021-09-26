package club.forhouse.services;

import club.forhouse.entities.ProfileCompanies;
import club.forhouse.repositories.ProfileContractorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileContractorServices {
    private final ProfileContractorRepository profileContractorRepository;

    public List<ProfileCompanies> findAll() {
        return profileContractorRepository.findAll();
    }

    public Optional<ProfileCompanies> findById(Long id) {
        return profileContractorRepository.findById(id);
    }

    //TODO надо дописать запрос для поиска компании по личному кабинету
//    public Company findCompanyById(LkCompany lkContractor) {
//        return null;
//    }


}
