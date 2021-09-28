package club.forhouse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDto {
    private Long materialId;

    private String category;
    private Long categoryId;

    private String name;
    private String description;
}
