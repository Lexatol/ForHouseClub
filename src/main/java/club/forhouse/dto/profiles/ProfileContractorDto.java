package club.forhouse.dto.profiles;

import club.forhouse.dto.pricelist.PriceListCompanyDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileContractorDto {
    private Long profileId;
    private CompanyDto company;
    private List<SpecializationDto> specializations;
    private PriceListCompanyDto priceListCompany;
}
