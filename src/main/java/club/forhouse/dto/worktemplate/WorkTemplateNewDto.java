package club.forhouse.dto.worktemplate;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class WorkTemplateNewDto {
    private String name;
    private String description;

    private List<WorkTemplateNewMaterialDto> materials;
    private List<WorkTemplateNewOperationDto> operations;

    @Data
    @NoArgsConstructor
    public static class WorkTemplateNewMaterialDto {
        private Long materialId;
        private int quantity;
    }

    @Data
    @NoArgsConstructor
    public static class WorkTemplateNewOperationDto {
        private Long operationId;
        private int quantity;
    }

}
