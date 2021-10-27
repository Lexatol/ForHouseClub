package club.forhouse.repositories.estimate;

import club.forhouse.entities.estimate.Estimate;
import club.forhouse.entities.estimate.EstimateWork;
import club.forhouse.entities.worktemplate.WorkCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstimateWorkRepository extends JpaRepository<EstimateWork, Long> {

    @Query("from EstimateWork as work where work.estimate = ?1 and work.workTemplate.category = ?2")
    List<EstimateWork> findAllByEstimateAndCategory(Estimate estimate, WorkCategory category);

    List<EstimateWork> findAllByEstimate(Estimate estimate);
}
