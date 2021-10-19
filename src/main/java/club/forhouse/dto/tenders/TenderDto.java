package club.forhouse.dto.tenders;

import club.forhouse.dto.estimate.EstimateDto;
import club.forhouse.entities.tenders.StatusTender;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenderDto {

    private Long tenderId;
    private String title;
    private String dataStart;
    private EstimateDto estimate;
    private String address;
    private TCompanyDto contractor;
    private TCompanyDto customer;
    private String description;
    private Long price;
    private StatusTender status;
}
