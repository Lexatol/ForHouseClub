package club.forhouse.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleCompanyDto {
    private Long companyId;
    private String companyName;
}
