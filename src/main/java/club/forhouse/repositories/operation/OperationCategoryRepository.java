package club.forhouse.repositories.operation;

import club.forhouse.entities.operation.OperationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationCategoryRepository extends JpaRepository<OperationCategory, Long> {
}
