package ForHouseClub.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "lk_contractors")
public class LkContractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lk_contractor_id")
    private Long LkContractorId;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany
    @JoinTable(name = "connection_contractor_customers",
            joinColumns = @JoinColumn(name = "contractor_id"),
            inverseJoinColumns = @JoinColumn(name = "company_customer_id "))
    @ToString.Exclude
    private List<Company> listCustomers;


    @OneToMany
    @JoinTable(name = "connection_contractor_providers",
            joinColumns = @JoinColumn(name = "contractor_id"),
            inverseJoinColumns = @JoinColumn(name = "company_provider_id "))
    @ToString.Exclude
    private List<Company> listProvider;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LkContractor that = (LkContractor) o;
        return Objects.equals(LkContractorId, that.LkContractorId);
    }

    @Override
    public int hashCode() {
        return LkContractorId != null ? LkContractorId.hashCode() : 0;
    }
}
