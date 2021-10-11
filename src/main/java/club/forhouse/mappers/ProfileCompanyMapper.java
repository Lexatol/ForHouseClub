package club.forhouse.mappers;

import club.forhouse.dto.ProfileContractorDto;
import club.forhouse.entities.ProfileCompany;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProfileCompanyMapper {
    ProfileContractorDto toDto(ProfileCompany profileCompany);
    List<ProfileContractorDto> toListDto(List<ProfileCompany> companyList);
    ProfileCompany toEntity(ProfileContractorDto profileContractorDto);
}
