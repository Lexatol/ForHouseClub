package club.forhouse.dto.estimate;

import club.forhouse.dto.operation.OperationDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EstimateOperationDto {

    private Long rowId;
    private EstimateDto estimate;
    private EstimateWorkDto estimateWork;
    private OperationDto operation;
    private Integer quantity;

}
