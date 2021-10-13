package club.forhouse.dto.estimate;

import club.forhouse.dto.material.MaterialDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EstimateMaterialDto {

    private Long rowId;
    private EstimateDto estimate;
    private EstimateOperationDto estimateOperation;
    private MaterialDto material;
    private Integer quantity;

}
