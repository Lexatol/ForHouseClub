package club.forhouse.dto.worktemplate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WorkTemplateNewDto extends WorkTemplateBaseDto {

    private List<WorkTemplateNewOperationDto> operations;

    @Data
    @NoArgsConstructor
    public static class WorkTemplateNewOperationDto {
        private Long operationId;
        private int quantity;
    }

}
