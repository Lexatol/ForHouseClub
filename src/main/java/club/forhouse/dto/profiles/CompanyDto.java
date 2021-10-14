package club.forhouse.dto.profiles;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private Long companyId;
    private String companyName;
    private String legalAddress;
    private String companyPhone;
    private String companyEmail;
    private Integer numberEmployees;
    private UserDto generalManager;
//    private List<UserDto> projectManagers;
//    private List<SimpleCompanyDto> listCustomers;
//    private List<SimpleCompanyDto> listProviders;
}

