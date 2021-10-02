package club.forhouse.dto.worktemplate;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class WorkTemplateNewDto {

    private String name;
    private String description;
    private List<WorkTemplateMaterialDto> materials;
    private List<WorkTemplateOperationDto> operations;

}
