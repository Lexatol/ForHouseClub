package club.forhouse.entities.estimate;

import club.forhouse.entities.material.Material;
import lombok.*;

import javax.persistence.*;

@Table(name = "estimates_materials")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class EstimateMaterial {

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
    @JoinColumn(name = "operation_id")
    @ToString.Exclude
    @NonNull
    private EstimateOperation estimateOperation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    @ToString.Exclude
    @NonNull
    private Material material;

    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstimateMaterial that = (EstimateMaterial) o;

        return rowId.equals(that.rowId);
    }

    @Override
    public int hashCode() {
        return rowId.hashCode();
    }
}
