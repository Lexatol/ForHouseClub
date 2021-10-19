package club.forhouse.dto.profiles;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceListCompanyDto {
    private Long id;
    private List<WorkDto> operation;
    private int priceOperation;
}

