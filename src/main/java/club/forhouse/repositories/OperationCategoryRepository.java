package club.forhouse.repositories;

import club.forhouse.entities.OperationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationCategoryRepository extends JpaRepository<OperationCategory, Long> {
}
