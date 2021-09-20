package ForHouseClub.dto;

import ForHouseClub.entities.Company;
import ForHouseClub.entities.LkContractor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
public class LkContractorDto {
    private Long LkContractorId;
    private CompanyDto companyDto;

    public LkContractorDto(LkContractor lkContractor) {
        this.LkContractorId = lkContractor.getLkContractorId();
        this.companyDto = new CompanyDto(lkContractor.getCompany());
    }


}
