package club.forhouse.entities;

import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "estimates")
@Entity
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

    @Column(name = "estimate_date")
    private LocalDate date;

    @Column(name = "address")
    private String address;
}
