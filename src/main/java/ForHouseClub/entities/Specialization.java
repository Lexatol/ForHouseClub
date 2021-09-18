package ForHouseClub.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "specialization")
public class Specialization {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id")
    private int speciaplizationId;

    @Column (name = "specialization_title")
    private String specializationTitle;
}
