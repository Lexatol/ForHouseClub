package club.forhouse.dto.profiles;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long roleId;
    private String roleName;
}
