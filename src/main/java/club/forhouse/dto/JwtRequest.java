package club.forhouse.dto;

import lombok.Data;

// обертка над пользователем, для json-на аутентификации
@Data
public class JwtRequest {
    private String username;
    private String password;
}
