package ForHouseClub.dto;

import ForHouseClub.entities.Specialization;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpecializationDto {

    private int specializationId;
    private String specializationTitle;

    public SpecializationDto(Specialization specialization) {
        this.specializationId = specialization.getSpecializationId();
        this.specializationTitle = specialization.getSpecializationTitle();
    }
}
