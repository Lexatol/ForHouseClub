package ForHouseClub.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table (name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long user_id;

    @Column(name = "username")
    private String userName;

    @Column(name ="password")
    private String userPassword;

    @Column(name = "email")
    private String userEmail;

    @Column(name = "phone")
    private String userPhone;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> userRoles;






}
