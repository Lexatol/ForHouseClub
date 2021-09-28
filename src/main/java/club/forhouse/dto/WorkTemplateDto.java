package club.forhouse.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class WorkTemplateDto {

    private Long templateId;
    private String name;
    private String description;

}
