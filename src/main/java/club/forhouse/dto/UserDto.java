package club.forhouse.dto;

import club.forhouse.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String phone;
    private String user_password;
//    private Collection<Role> roles;

    public UserDto(User user) {
        this.username = user.getUserName();
        this.email = user.getUserEmail();
        this.phone = user.getUserPhone();
        this.user_password = user.getUserPassword();
//        this.roles = user.getUserRoles();
    }
}
