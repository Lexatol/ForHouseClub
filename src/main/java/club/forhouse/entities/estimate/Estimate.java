package club.forhouse.entities.estimate;

import club.forhouse.entities.profiles.Company;
import club.forhouse.entities.profiles.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "estimates")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class Estimate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estimate_id")
    private Long estimateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @ToString.Exclude
    @NonNull
    private Company company;

    @Column(name = "estimate_number")
    private Integer number;

    @Column(name = "estimate_sum")
    private Integer sum;

    @Column(name = "estimate_date")
    private LocalDateTime date;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    @ToString.Exclude
    private User author;

    @OneToMany(mappedBy = "estimate", orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<EstimateWork> works;

    public void addWork(EstimateWork work) {
        works.add(work);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estimate estimate = (Estimate) o;

        return estimateId.equals(estimate.estimateId);
    }

    @Override
    public int hashCode() {
        return estimateId.hashCode();
    }
}
