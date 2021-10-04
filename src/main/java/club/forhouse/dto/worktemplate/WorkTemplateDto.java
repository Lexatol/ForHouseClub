package club.forhouse.dto.worktemplate;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class WorkTemplateDto {

    private Long templateId;
    private String name;
    private String description;
    private List<WorkTemplateMaterialDto> materials;
    private List<WorkTemplateOperationDto> operations;

}
