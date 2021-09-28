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
    private String user_password;
    private String photo;

    public UserDto(User user) {
        this.id = user.getUserId();
        this.username = user.getUserName();
        this.email = user.getUserEmail();
        this.phone = user.getUserPhone();
        this.user_password = user.getUserPassword();
        this.photo = user.getPhoto();
    }
}
