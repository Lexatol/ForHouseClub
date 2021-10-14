package club.forhouse.entities.tenders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "status")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StatusTender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "title")
    private String status;
}
