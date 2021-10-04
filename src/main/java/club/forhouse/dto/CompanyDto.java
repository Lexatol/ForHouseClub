package club.forhouse.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private Long companyId;
    private String companyName;
    private String legalAddress;
    private String phone;
    private String email;
    private Integer numberEmployees;
    private UserDto generalManager;
    private List<UserDto> projectManagers;
    private List<SimpleCompanyDto> listCustomers;
    private List<SimpleCompanyDto> listProviders;
}

