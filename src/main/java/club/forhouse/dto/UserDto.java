package club.forhouse.dto;

import club.forhouse.entities.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String photo;

    public UserDto(User user) {
        this.id = user.getUserId();
        this.username = user.getUserName();
        this.email = user.getUserEmail();
        this.phone = user.getUserPhone();
        this.photo = user.getPhoto();
    }
}
