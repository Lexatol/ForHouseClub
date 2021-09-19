package ForHouseClub.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table (name = "profile_companies")
public class ProfileContractor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "profile_id")
    private Long profileId;

    @OneToOne
    @JoinColumn (name = "lk_contractor_id")
    private LkContractor lkContractor;

    @OneToMany
    @JoinColumn(name = "specialization_id")
    private List<Specialization> specializations;
}
