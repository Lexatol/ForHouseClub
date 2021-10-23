package club.forhouse.repositories.worktemplate;

import club.forhouse.entities.worktemplate.WorkCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkCategoryRepository extends JpaRepository<WorkCategory, Long> {
}
