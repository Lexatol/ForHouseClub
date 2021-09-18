package ForHouseClub.services;

import ForHouseClub.entities.Company;
import ForHouseClub.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServices {
    public final CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

}
