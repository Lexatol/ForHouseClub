package club.forhouse.entities.estimate;

import club.forhouse.entities.worktemplate.WorkTemplate;
import lombok.*;

import javax.persistence.*;

@Table(name = "estimates_works")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class EstimateWork {
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
    @JoinColumn(name = "works_template")
    @ToString.Exclude
    @NonNull
    private WorkTemplate workTemplate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstimateWork that = (EstimateWork) o;

        return rowId.equals(that.rowId);
    }

    @Override
    public int hashCode() {
        return rowId.hashCode();
    }
}
