package ForHouseClub.dto;

import ForHouseClub.entities.Company;
import ForHouseClub.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@NoArgsConstructor
public class CompanyDto {
    private Long id;
    private String companyName;
    private String companyAddress;
    private String companyPhone;
    private String companyEmail;
    private String compositionAndNumber;
    private User generalManager;
    private List<User> projectManagers;

    public CompanyDto(Company company) {
        this.id = company.getCompanyId();
        this.companyName = company.getCompanyName();
        this.companyAddress = company.getCompanyAddress();
        this.companyPhone = company.getCompanyPhone();
        this.companyEmail = company.getCompanyEmail();
//        this.compositionAndNumber = company.getCompositionAndNumber();
//        this.generalManager = company.getGeneralManager();
//        this.projectManagers = company.getProjectManagers();
    }
}
