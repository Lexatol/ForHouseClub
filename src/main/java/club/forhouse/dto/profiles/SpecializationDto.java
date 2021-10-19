package club.forhouse.dto.profiles;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecializationDto {
    private Long specializationId;
    private String specializationTitle;
}
