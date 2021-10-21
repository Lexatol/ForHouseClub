package club.forhouse.dto.profiles;

import club.forhouse.entities.profiles.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LkUserDto {
    private Long lkUserId;
    private User user;
    private String userPosition;
}
