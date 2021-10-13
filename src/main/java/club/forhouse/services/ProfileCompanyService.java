package club.forhouse.services;

import club.forhouse.dto.ProfileContractorDto;
import club.forhouse.dto.SpecializationDto;
import club.forhouse.entities.ProfileCompany;
import club.forhouse.entities.Specialization;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.ProfileCompanyMapper;
import club.forhouse.repositories.ProfileCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileCompanyService {
    private final ProfileCompanyRepository profileCompanyRepository;
    private final ProfileCompanyMapper profileCompanyMapper;
    private final CompanyService companyService;
    private final SpecializationServices specializationServices;

    public List<ProfileContractorDto> findAll() {
        return profileCompanyMapper.toListDto(profileCompanyRepository.findAll());
    }

    public ProfileContractorDto findById(Long id) {
        ProfileCompany pfC = profileCompanyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find profile with id: " + id));
        return profileCompanyMapper.toDto(pfC);
    }

    public ProfileContractorDto findCompanyByGeneralManagerEmail(String email) {
        ProfileCompany pfC = profileCompanyRepository.findCompanyByGeneralManagerEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find profile with id: " + email));
        return profileCompanyMapper.toDto(pfC);
    }

    public ProfileContractorDto saveOrUpdate(ProfileContractorDto profileContractorDto) {
        ProfileCompany profileCompany = profileCompanyMapper.toProfileCompany(profileContractorDto);
        profileCompany = profileCompanyRepository.save(profileCompany);
        return profileCompanyMapper.toDto(profileCompany);
    }

    public void saveProfileFromName(String companyName) {
        ProfileCompany profileCompany = new ProfileCompany();
        profileCompany.setCompany(companyService.findByName(companyName));

        List<Specialization> specializationList = new ArrayList<>();
        specializationList.add(specializationServices.findSpecById(1L));

        // need fix
        profileCompany.setSpecializations(null);

        profileCompanyRepository.save(profileCompany);
    }
}
