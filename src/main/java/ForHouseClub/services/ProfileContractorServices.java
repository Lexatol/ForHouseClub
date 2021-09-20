package ForHouseClub.services;

import ForHouseClub.entities.Company;
import ForHouseClub.entities.LkContractor;
import ForHouseClub.entities.ProfileContractor;
import ForHouseClub.repositories.ProfileContractorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileContractorServices {
    private final ProfileContractorRepository profileContractorRepository;

    public List<ProfileContractor> findAll() {
        return profileContractorRepository.findAll();
    }

    public Optional<ProfileContractor> findById(Long id) {
        return profileContractorRepository.findById(id);
    }

    //TODO надо дописать запрос для поиска компании по личному кабинету
//    public Company findCompanyById(LkContractor lkContractor) {
//        return null;
//    }


}
