package club.forhouse.dto.estimate;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class EstimateDto extends EstimateBaseDto {
    private List<EstimateWorkDto> works;
}
