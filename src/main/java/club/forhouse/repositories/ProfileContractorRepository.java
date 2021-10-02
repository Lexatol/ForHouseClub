package club.forhouse.repositories;

import club.forhouse.entities.Company;
import club.forhouse.entities.ProfileCompanies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileContractorRepository extends JpaRepository<ProfileCompanies, Long> {
    @Query("select pc from ProfileCompanies pc where pc.company.generalManager.userEmail = ?1")
    Optional<ProfileCompanies> findCompanyByGeneralManagerEmail(String managerEmail);

    //TODO сюда надо написать SQL запрос чтобы найти компанию
//    public Company getCompany(Long id);
}
