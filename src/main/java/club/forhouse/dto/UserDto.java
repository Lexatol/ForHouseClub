package club.forhouse.dto;

import club.forhouse.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String photo;
//    private Collection<Role> roles;

    public UserDto(User user) {
        this.id = user.getUser_id();
        this.username = user.getUserName();
        this.email = user.getUserEmail();
        this.phone = user.getUserPhone();
        this.photo = user.getPhoto();
//        this.roles = user.getUserRoles();
    }
}
