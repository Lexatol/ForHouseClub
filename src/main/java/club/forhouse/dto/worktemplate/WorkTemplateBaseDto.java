package club.forhouse.dto.worktemplate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkTemplateBaseDto {

    private Long templateId;
    private String name;
    private String description;

}
