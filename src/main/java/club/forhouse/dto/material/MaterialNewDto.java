package club.forhouse.dto.material;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialNewDto {
    private Long categoryId;
    private String name;
    private String description;
}
