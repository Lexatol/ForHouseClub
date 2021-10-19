package club.forhouse.services.profiles;

import club.forhouse.dto.profiles.CompanyDto;
import club.forhouse.dto.profiles.UserDto;
import club.forhouse.entities.profiles.Company;
import club.forhouse.entities.profiles.User;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.CompanyMapper;
import club.forhouse.mappers.UserMapper;
import club.forhouse.repositories.profiles.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final UserService userService;

    private final UserMapper userMapper;

    public List<CompanyDto> findAll() {
        return companyMapper.toListDto(companyRepository.findAll());
    }

    public CompanyDto findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Company with id: " + id));
        return companyMapper.toDto(company);
    }

    public CompanyDto saveCompanyFromName(String companyName, String generalManager) {
        Company company = new Company();
        company.setCompanyName(companyName);
        company.setGeneralManager(userService.findByUserName(generalManager));
        companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    public Company findByName(String companyName) {
        Company company = companyRepository.findByCompanyName(companyName).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Company with name: " + companyName));
        return company;
    }

    public CompanyDto findByUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        Company company = companyRepository.findByGeneralManager(user).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find Company for manager")
        );
        return companyMapper.toDto(company);
    }

}
