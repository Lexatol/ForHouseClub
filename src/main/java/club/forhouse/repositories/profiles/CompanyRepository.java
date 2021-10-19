package club.forhouse.repositories.profiles;

import club.forhouse.entities.profiles.Company;
import club.forhouse.entities.profiles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("select c from Company c where c.generalManager.userEmail = ?1")
    Optional<Company> findCompanyByGeneralManagerEmail(String manager);

    Optional<Company> findByCompanyName(String companyName);

    Optional<Company> findByGeneralManager(User manager);
}
