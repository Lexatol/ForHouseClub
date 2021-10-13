package club.forhouse.repositories;

import club.forhouse.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select c from Company c where c.generalManager.userEmail = ?1")
    Optional<Company> findCompanyByGeneralManagerEmail(String manager);

    Optional<Company> findByCompanyName(String companyName);
}
