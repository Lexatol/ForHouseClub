package club.forhouse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MaterialCategoryDto {
    @NonNull
    private Long categoryId;
    private String name;
    private String description;
}
