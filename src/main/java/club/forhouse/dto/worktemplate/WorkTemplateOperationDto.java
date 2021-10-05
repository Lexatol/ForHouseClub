package club.forhouse.dto.worktemplate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkTemplateOperationDto {

    private Long rowId;

    private Long templateId;
    private String template;

    private Long operationId;
    private String operation;

    private int quantity = 0;

}
