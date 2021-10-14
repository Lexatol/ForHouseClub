package club.forhouse.repositories.tenders;

import club.forhouse.entities.tenders.TenderPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TendersPlatformRepository extends JpaRepository<TenderPlatform, Long> {
}
