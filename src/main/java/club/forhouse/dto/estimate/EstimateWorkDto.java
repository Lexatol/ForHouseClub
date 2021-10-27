package club.forhouse.dto.estimate;

import club.forhouse.dto.worktemplate.WorkTemplateDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EstimateWorkDto {

    private Long rowId;
    private EstimateDto estimate;
    private WorkTemplateDto workTemplate;

}
