package club.forhouse.dto.tenders;

import club.forhouse.entities.tenders.Tender;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenderPlatformDto {

    private Long platformId;
    private String title;
    private List<TenderDto> tenders;
}
