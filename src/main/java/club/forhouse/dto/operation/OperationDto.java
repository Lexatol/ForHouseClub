package club.forhouse.dto.operation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OperationDto {
    private Long operationId;

    private String category;
    private Long categoryId;

    private String name;
    private String description;
}
