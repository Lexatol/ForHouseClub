package club.forhouse.dto;

import club.forhouse.entities.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

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
    private Collection<Role> userRoles;
}
