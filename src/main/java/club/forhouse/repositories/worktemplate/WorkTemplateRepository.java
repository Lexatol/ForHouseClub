package club.forhouse.repositories.worktemplate;

import club.forhouse.entities.worktemplate.WorkTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTemplateRepository extends JpaRepository<WorkTemplate, Long> {
}
