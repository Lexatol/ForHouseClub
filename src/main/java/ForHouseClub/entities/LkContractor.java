package ForHouseClub.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table (name = "lk_contractors")
public class LkContractor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "lk_contractor_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
