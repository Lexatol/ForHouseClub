package club.forhouse.dto;


import club.forhouse.entities.ProfileCompanies;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProfileContractorDto {
    private Long profileId;
    private CompanyDto companyDto;
    private List<SpecializationDto> specializations;

    public ProfileContractorDto(ProfileCompanies profileCompanies) {
        this.profileId = profileCompanies.getProfileId();
        this.companyDto = new CompanyDto(profileCompanies.getCompany());
        this.specializations = profileCompanies.getSpecializations()
                .stream().map(SpecializationDto::new).collect(Collectors.toList());
    }
}
