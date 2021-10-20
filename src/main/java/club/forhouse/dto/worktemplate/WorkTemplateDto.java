package club.forhouse.dto.worktemplate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WorkTemplateDto extends WorkTemplateBaseDto {

    private Long templateId;
    private List<WorkTemplateOperationDto> operations;

}
