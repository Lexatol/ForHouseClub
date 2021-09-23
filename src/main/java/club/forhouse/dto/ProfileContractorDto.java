package club.forhouse.dto;


import club.forhouse.entities.ProfileContractor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProfileContractorDto {
    private Long profileId;
    private LkCompanyDto lkCompanyDto;
    private List<SpecializationDto> specializations;

    public ProfileContractorDto(ProfileContractor profileContractor) {
        this.profileId = profileContractor.getProfileId();
        this.lkCompanyDto = new LkCompanyDto(profileContractor.getLkCompany());
        this.specializations = profileContractor.getSpecializations()
                .stream().map(SpecializationDto::new).collect(Collectors.toList());
    }
}
