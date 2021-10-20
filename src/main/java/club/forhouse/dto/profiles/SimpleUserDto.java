package club.forhouse.dto.profiles;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserDto {
    private Long userId;
    private String userEmail;
    private String userName;
}
