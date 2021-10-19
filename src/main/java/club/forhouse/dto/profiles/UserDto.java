package club.forhouse.dto.profiles;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String photo;
//    private Collection<Role> userRoles;
}
