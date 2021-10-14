package club.forhouse.repositories.estimate;

import club.forhouse.entities.estimate.EstimateMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimateMaterialRepository extends JpaRepository<EstimateMaterial, Long> {
}
