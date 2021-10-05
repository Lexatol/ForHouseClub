package club.forhouse.repositories;

import club.forhouse.entities.ProfileCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileCompanyRepository extends JpaRepository<ProfileCompany, Long> {
    @Query("select pc from ProfileCompany pc where pc.company.generalManager.userEmail = ?1")
    Optional<ProfileCompany> findCompanyByGeneralManagerEmail(String managerEmail);
}
