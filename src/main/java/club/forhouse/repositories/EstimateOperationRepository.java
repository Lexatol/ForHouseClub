package club.forhouse.repositories;

import club.forhouse.entities.estimate.EstimateOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimateOperationRepository extends JpaRepository<EstimateOperation, Long> {
}
