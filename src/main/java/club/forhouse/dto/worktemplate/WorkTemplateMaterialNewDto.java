package club.forhouse.dto.worktemplate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkTemplateMaterialNewDto {
    private Long templateId;
    private Long materialId;
    private int quantity = 0;
}
