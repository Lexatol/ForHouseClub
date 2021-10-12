package club.forhouse.dto;

import club.forhouse.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SystemUserDto {
    private Long userId;

    @Size(min = 4, message = "user name must be more than 4 characters")
    private String userName;

    @Email(message = "wrong format e-mail")
    private String userEmail;
    private String userPassword;
    private String userPhone;
    private String confirmationPassword;
    private String companyName;

    /*public SystemUserDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getUserName();
        this.email = user.getUserEmail();
        this.phone = user.getUserPhone();
    }*/
}
