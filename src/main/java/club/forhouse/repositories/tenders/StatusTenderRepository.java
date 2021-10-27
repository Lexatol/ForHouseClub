package club.forhouse.repositories.tenders;

import club.forhouse.entities.tenders.StatusTender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusTenderRepository extends JpaRepository<StatusTender, Long> {
}
