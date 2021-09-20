package ForHouseClub.dto;

import ForHouseClub.entities.LkContractor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LkContractorDto {
    private Long LkContractorId;
    private CompanyDto companyDto;
    private List<CompanyDto> companyCustomer;
    private List<CompanyDto> companyProvider;

    public LkContractorDto(LkContractor lkContractor) {
        this.LkContractorId = lkContractor.getLkContractorId();
        this.companyDto = new CompanyDto(lkContractor.getCompany());
        this.companyCustomer = lkContractor.getListCustomers()
                .stream().map(CompanyDto::new).collect(Collectors.toList());
        this.companyProvider = lkContractor.getListProvider()
                .stream().map((CompanyDto::new)).collect(Collectors.toList());
    }
}
