package club.forhouse.dto.pricelist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceListItemDto {

    private OperDto operation;
    private Long price;
}
