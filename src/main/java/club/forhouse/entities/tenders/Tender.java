package club.forhouse.entities.tenders;

import club.forhouse.entities.profiles.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tenders")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tender_id")
    private Long tenderId;

    @Column(name = "title")
    private String title;

    @Column(name = "data_start")
    private String dataStart;

//    @OneToOne (cascade = CascadeType.ALL)
//    @JoinColumn(name = "estimate_id")
//    private Estimate estimate;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Company contractor;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Company customer;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @OneToOne
    @JoinColumn(name = "status_id")
    private StatusTender status;

    @ManyToOne
    @JoinColumn(name = "platform_id")
    private TenderPlatform tenderPlatform;
}
