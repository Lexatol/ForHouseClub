package club.forhouse.dto.estimate;

import club.forhouse.dto.worktemplate.WorkTemplateBaseDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EstimateWorkDto {

    private Long rowId;
    private EstimateBaseDto estimate;
    private WorkTemplateBaseDto workTemplate;

}
