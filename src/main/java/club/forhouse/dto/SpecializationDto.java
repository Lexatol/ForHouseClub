package club.forhouse.dto;

import club.forhouse.entities.Specialization;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecializationDto {

    private Long id;
    private String title;

    public SpecializationDto(Specialization specialization) {
        this.id = specialization.getSpecializationId();
        this.title = specialization.getSpecializationTitle();
    }
}
