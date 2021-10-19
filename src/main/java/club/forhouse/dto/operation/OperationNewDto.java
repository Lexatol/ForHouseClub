package club.forhouse.dto.operation;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OperationNewDto {
    private Long categoryId;
    private String name;
    private String description;
}
