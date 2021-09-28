package club.forhouse.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class WorkTemplateDto {

    private Long templateId;
    private String name;
    private String description;
    private List<WorkTemplateMaterialDto> materials;
    private List<WorkTemplateOperationDto> operations;

}
