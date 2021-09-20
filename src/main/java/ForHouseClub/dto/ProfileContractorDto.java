package ForHouseClub.dto;


import ForHouseClub.entities.LkContractor;
import ForHouseClub.entities.ProfileContractor;
import ForHouseClub.entities.Specialization;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfileContractorDto {
    private Long profileId;
    private LkContractorDto lkContractorDto;
    private List<Specialization> specializations;

    public ProfileContractorDto(ProfileContractor profileContractor) {
        this.profileId = profileContractor.getProfileId();
        this.lkContractorDto = new LkContractorDto(profileContractor.getLkContractor());
        this.specializations = profileContractor.getSpecializations();
    }
}
