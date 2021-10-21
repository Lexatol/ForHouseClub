package club.forhouse.entities.profiles;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile_users")
public class LkUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long lkUserId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_position")
    private String userPosition;
}
