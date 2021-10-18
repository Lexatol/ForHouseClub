package club.forhouse.entities.pricelist;

import club.forhouse.entities.operation.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "price_items")
public class PriceListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "operation_id")
    private Operation operation;

    @Column(name = "price")
    private Long price;
}
