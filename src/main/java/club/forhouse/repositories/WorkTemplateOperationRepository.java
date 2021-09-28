package club.forhouse.repositories;

import club.forhouse.entities.WorkTemplate;
import club.forhouse.entities.WorkTemplateOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTemplateOperationRepository extends JpaRepository<WorkTemplateOperation, Long> {
    List<WorkTemplateOperation> findAllByTemplateId(WorkTemplate template);
}
