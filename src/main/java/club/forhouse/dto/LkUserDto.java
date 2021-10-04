package club.forhouse.dto;

import club.forhouse.entities.User;
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
