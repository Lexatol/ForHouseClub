package club.forhouse.repositories.worktemplate;

import club.forhouse.entities.worktemplate.WorkCategory;
import club.forhouse.entities.worktemplate.WorkTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTemplateRepository extends JpaRepository<WorkTemplate, Long> {
    List<WorkTemplate> findAllByCategory(WorkCategory category);
}
