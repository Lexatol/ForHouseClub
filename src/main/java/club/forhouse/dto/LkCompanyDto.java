package club.forhouse.dto;

import club.forhouse.entities.LkCompany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LkCompanyDto {
    private Long LkContractorId;
    private CompanyDto companyDto;
    private List<CompanyDto> companyCustomer;
    private List<CompanyDto> companyProvider;

    public LkCompanyDto(LkCompany lkCompany) {
        this.LkContractorId = lkCompany.getLkCompanyId();
        this.companyDto = new CompanyDto(lkCompany.getCompany());
        this.companyCustomer = lkCompany.getListCustomers()
                .stream().map(CompanyDto::new).collect(Collectors.toList());
        this.companyProvider = lkCompany.getListProvider()
                .stream().map((CompanyDto::new)).collect(Collectors.toList());
    }
}
