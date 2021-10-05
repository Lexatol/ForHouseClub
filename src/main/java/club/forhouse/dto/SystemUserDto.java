package club.forhouse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class SystemUserDto {

    @Size(min = 4, message = "user name must be more than 4 characters")
    private String name;

    @Email(message = "wrong format e-mail")
    private String email;
    private String password;
    private String confirmationPassword;
}
