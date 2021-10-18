package club.forhouse.dto.pricelist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceListCompanyDto {

    private Long priceId;
    private List<PriceListItemDto> operation;
}

