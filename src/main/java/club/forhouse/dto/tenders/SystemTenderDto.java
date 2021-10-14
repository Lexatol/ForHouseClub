package club.forhouse.dto.tenders;

import club.forhouse.dto.estimate.EstimateDto;
import club.forhouse.entities.profiles.Company;
import club.forhouse.entities.tenders.StatusTender;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemTenderDto {
    private String title;
    private String dataStart;
    private String address;
    private Company customer;
    private String description;
    private Long price;
    private StatusTender status;
}
