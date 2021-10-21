package club.forhouse.entities.pricelist;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prices")
public class PriceListCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long priceId;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "pricelist",
            joinColumns = @JoinColumn(name = "price_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id "))
    private List<PriceListItem> operation;
}

