package club.forhouse.dto.worktemplate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkTemplateOperationNewDto {
    private Long templateId;
    private Long operationId;
    private int quantity = 0;
}
