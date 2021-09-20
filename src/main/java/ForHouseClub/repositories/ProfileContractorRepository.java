package ForHouseClub.repositories;

import ForHouseClub.entities.Company;
import ForHouseClub.entities.ProfileContractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileContractorRepository extends JpaRepository<ProfileContractor, Long> {

    //TODO сюда надо написать SQL запрос чтобы найти компанию
//    public Company getCompany(Long id);
}
