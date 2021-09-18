package ForHouseClub.repositories;

import ForHouseClub.entities.LkContractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LkContractorRepository extends JpaRepository<LkContractor, Long> {
}
