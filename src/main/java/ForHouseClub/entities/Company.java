package ForHouseClub.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name ="company_id")
    private Long companyId;

    @Column (name = "company_name")
    private String companyName;

    @Column (name = "company_address")
    private String companyAddress;

    @Column (name = "company_phone")
    private String companyPhone;

    @Column (name = "company_email")
    private String companyEmail;

    @Column (name ="compositionAndNumber")
    private String compositionAndNumber;

    @OneToOne
    @JoinColumn (name = "general_manager")
    private User generalManager;

    @OneToMany
    @JoinColumn (name = "project_manager")
    private List<User> projectManagers;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> users;
}
