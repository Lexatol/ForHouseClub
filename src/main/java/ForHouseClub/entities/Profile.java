package ForHouseClub.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table (name = "profile_companies")
public class Profile {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "profile_id")
    private Long id;

    @Column (name = "lk_contractor_id")
    private LkContractor lkContractor;

    @OneToMany
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;
}
