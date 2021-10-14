package club.forhouse.entities.tenders;

import club.forhouse.entities.profiles.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "platforms")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TenderPlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "platform_id")
    private Long platformId;

    @Column(name = "title")
    private String title;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinTable(name = "platform_tenders",
            joinColumns = @JoinColumn(name = "platform_id"),
            inverseJoinColumns = @JoinColumn(name = "tender_id"))
    private List<Tender> tenders;
}
