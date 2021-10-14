package club.forhouse.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Data;

// Токен отправляется клиенту в виде json
@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
}
