package ForHouseClub.repositories;

import ForHouseClub.entities.ProfileContractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileContractorRepository extends JpaRepository<ProfileContractor, Long> {
}
