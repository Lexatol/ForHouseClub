package club.forhouse.dto.tenders;

import club.forhouse.dto.estimate.EstimateDto;
import club.forhouse.entities.profiles.Company;
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
    private Company contractor;
    private Company customer;
    private String description;
    private Long price;
    private String status;
}
