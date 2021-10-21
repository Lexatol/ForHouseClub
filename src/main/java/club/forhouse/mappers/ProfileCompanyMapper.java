package club.forhouse.mappers;

import club.forhouse.dto.profiles.ProfileContractorDto;
import club.forhouse.entities.profiles.ProfileCompany;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProfileCompanyMapper {
    ProfileContractorDto toDto(ProfileCompany profileCompany);

    @InheritInverseConfiguration
    ProfileCompany toProfileCompany(ProfileContractorDto profileContractorDto);

    List<ProfileContractorDto> toListDto(List<ProfileCompany> companyList);
    ProfileCompany toEntity(ProfileContractorDto profileContractorDto);
}
