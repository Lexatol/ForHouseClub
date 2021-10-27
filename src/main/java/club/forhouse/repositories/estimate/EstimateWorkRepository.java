package club.forhouse.repositories.estimate;

import club.forhouse.entities.estimate.EstimateWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimateWorkRepository extends JpaRepository<EstimateWork, Long> {
}
