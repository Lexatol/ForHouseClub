package club.forhouse.dto;

import club.forhouse.entities.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CompanyDto {
    private Long id;
    private String companyName;
    private String legalAddress;
    private String companyPhone;
    private String companyEmail;
    private Integer compositionAndNumber;
    private UserDto generalManager;
    private List<UserDto> projectManagers;
    private List<CompanyDto> listCustomers;
    private List<CompanyDto> listProviders;

    public CompanyDto(Company company) {
        this.id = company.getCompanyId();
        this.companyName = company.getCompanyName();
        this.legalAddress = company.getLegalAddress();
        this.companyPhone = company.getCompanyPhone();
        this.companyEmail = company.getCompanyEmail();
        this.compositionAndNumber = company.getCompositionAndNumber();
        this.generalManager = new UserDto(company.getGeneralManager());
        this.projectManagers = company.getProjectManagers().stream().map(UserDto::new).collect(Collectors.toList());
//        this.listCustomers = company.getListCustomers().stream().map(CompanyDto::new).collect(Collectors.toList());
//        this.listProviders = company.getListProviders().stream().map(CompanyDto::new).collect(Collectors.toList());
    }
}
