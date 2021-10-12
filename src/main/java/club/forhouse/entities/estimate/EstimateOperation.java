package club.forhouse.entities.estimate;

import club.forhouse.entities.Operation;
import lombok.*;

import javax.persistence.*;

@Table(name = "estimates_works")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class EstimateOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    @NonNull
    private Long rowId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estimate_id")
    @ToString.Exclude
    @NonNull
    private Estimate estimate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    @ToString.Exclude
    @NonNull
    private EstimateWork estimateWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_id")
    @ToString.Exclude
    @NonNull
    private Operation operation;

    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstimateOperation that = (EstimateOperation) o;

        return rowId.equals(that.rowId);
    }

    @Override
    public int hashCode() {
        return rowId.hashCode();
    }
}
