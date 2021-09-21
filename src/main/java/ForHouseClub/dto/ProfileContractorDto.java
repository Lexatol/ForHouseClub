package ForHouseClub.dto;


import ForHouseClub.entities.ProfileContractor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProfileContractorDto {
    private Long profileId;
    private LkContractorDto lkContractorDto;
    private List<SpecializationDto> specializations;

    public ProfileContractorDto(ProfileContractor profileContractor) {
        this.profileId = profileContractor.getProfileId();
        this.lkContractorDto = new LkContractorDto(profileContractor.getLkContractor());
        this.specializations = profileContractor.getSpecializations()
                .stream().map(SpecializationDto::new).collect(Collectors.toList());
    }
}
