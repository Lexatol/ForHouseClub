package club.forhouse.repositories;

import club.forhouse.entities.ProfileCompanies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileContractorRepository extends JpaRepository<ProfileCompanies, Long> {

    //TODO сюда надо написать SQL запрос чтобы найти компанию
//    public Company getCompany(Long id);
}
