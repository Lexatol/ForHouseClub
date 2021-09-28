package club.forhouse.repositories;

import club.forhouse.entities.WorkTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTemplateRepository extends JpaRepository<WorkTemplate, Long> {
}
