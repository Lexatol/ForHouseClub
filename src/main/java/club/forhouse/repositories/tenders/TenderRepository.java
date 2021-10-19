package club.forhouse.repositories.tenders;

import club.forhouse.entities.tenders.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {
}
