package ForHouseClub.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table (name = "lk_contractors")
public class LkContractor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "lk_contractor_id")
    private Long LkContractorId;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
