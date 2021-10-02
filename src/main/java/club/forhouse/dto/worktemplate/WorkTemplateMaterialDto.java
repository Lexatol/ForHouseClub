package club.forhouse.dto.worktemplate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkTemplateMaterialDto {

    private Long rowId;

    private Long templateId;
    private String template;

    private Long materialId;
    private String material;

    private int quantity = 0;

}
