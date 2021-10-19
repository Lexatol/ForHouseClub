package club.forhouse.entities.profiles;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "companies")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "legal_address")
    private String legalAddress;

    @Column(name = "company_phone")
    private String companyPhone;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "number_Employees")
    private Integer numberEmployees;

    @OneToOne
    @JoinColumn(name = "general_manager")
    private User generalManager;

//    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
//    private PriceListCompany priceListCompany;

    // TODO надо переделать и продумать механизм добавления именно из юзером только проджект менеджеров
    @OneToMany
    @JoinTable(name = "companies_pm",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ToString.Exclude
    private List<User> projectManagers;

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
    private List<Company> listProviders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company company = (Company) o;
        return Objects.equals(companyId, company.companyId);
    }

    @Override
    public int hashCode() {
        return companyId != null ? companyId.hashCode() : 0;
    }


}
