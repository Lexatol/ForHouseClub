package club.forhouse.services;

import club.forhouse.dto.ProfileContractorDto;
import club.forhouse.entities.ProfileCompany;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.ProfileCompanyMapper;
import club.forhouse.repositories.ProfileCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileCompanyService {
    private final ProfileCompanyRepository profileCompanyRepository;
    private final ProfileCompanyMapper profileCompanyMapper;

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
}
