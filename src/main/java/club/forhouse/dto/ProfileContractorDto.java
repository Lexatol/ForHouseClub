package club.forhouse.dto;


import club.forhouse.entities.ProfileCompany;
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

    public ProfileContractorDto(ProfileCompany profileCompany) {
        this.profileId = profileCompany.getProfileId();
        this.companyDto = new CompanyDto(profileCompany.getCompany());
        this.specializations = profileCompany.getSpecializations()
                .stream().map(SpecializationDto::new).collect(Collectors.toList());
    }
}
