package club.forhouse.services;

import club.forhouse.dto.ProfileContractorDto;
import club.forhouse.entities.ProfileCompany;
import club.forhouse.exceptions.ResourceNotFoundException;
import club.forhouse.mappers.ProfileCompanyMapper;
import club.forhouse.repositories.ProfileCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileCompanyService {
    private final ProfileCompanyRepository profileCompanyRepository;
    private final ProfileCompanyMapper profileCompanyMapper;

    public List<ProfileContractorDto> findAll() {
        return profileCompanyMapper.toListDto(profileCompanyRepository.findAll())  ;
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
        profileCompanyRepository.save(profileCompany);
        return profileCompanyMapper.toDto(profileCompany);
    }

    /*public ProfileCompany findCompanyByEmailForSave(String email) {
        ProfileCompany pfC = profileCompanyRepository.findCompanyByGeneralManagerEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find profile with id: " + email));
        return pfC;
    }

    public void saveOrUpdate(ProfileContractorDto profileContractorDto) {
        ProfileCompany pfC = profileCompanyRepository.findCompanyByGeneralManagerEmail(profileContractorDto.getCompany().getGeneralManager().getUserEmail()).orElseThrow(() ->
                new ResourceNotFoundException("Unable to find profile"));

        pfC.setCompany(profileCompanyMapper.toEntity(profileContractorDto).getCompany());
        pfC.setSpecializations(profileCompanyMapper.toEntity(profileContractorDto).getSpecializations());

        //System.out.println(pfC.getSpecializations());

        //System.out.println(profileCompanyMapper.toEntity(profileContractorDto).getCompany());
        //System.out.println(profileCompanyMapper.toEntity(profileContractorDto).getSpecializations().get(0).getSpecializationId());
        //

        // ??? RROR 21896 --- [nio-8189-exec-7] o.h.engine.jdbc.spi.SqlExceptionHelper   : Нарушение ссылочной целостности
        // profileCompanyRepository.save(pfC);
    }*/
}
