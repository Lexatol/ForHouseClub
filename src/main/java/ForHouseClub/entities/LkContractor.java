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
    private Long LkContractorId;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany
    @JoinTable(name = "connection_contractor_customers",
            joinColumns = @JoinColumn(name = "contractor_id"),
            inverseJoinColumns = @JoinColumn(name = "company_customer_id "))
    private List<Company> listCustomers;


    @OneToMany
    @JoinTable(name = "connection_contractor_providers",
            joinColumns = @JoinColumn(name = "contractor_id"),
            inverseJoinColumns = @JoinColumn(name = "company_provider_id "))
    private List<Company> listProvider;





}
