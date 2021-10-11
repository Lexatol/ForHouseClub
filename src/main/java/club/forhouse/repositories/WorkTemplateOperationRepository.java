package club.forhouse.repositories;

import club.forhouse.entities.OperationCategory;
import club.forhouse.entities.WorkTemplate;
import club.forhouse.entities.WorkTemplateOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTemplateOperationRepository extends JpaRepository<WorkTemplateOperation, Long> {
    List<WorkTemplateOperation> findAllByTemplateId(WorkTemplate template);

    @Query("from WorkTemplateOperation as wto where wto.operationId.category = ?1")
    List<WorkTemplateOperation> findAllByOperationCategory(OperationCategory category);

    @Query("from WorkTemplateOperation as wto where wto.operationId.category = ?1 AND wto.templateId = ?2")
    List<WorkTemplateOperation> findAllByOperationCategoryAndTemplate(OperationCategory category, WorkTemplate template);
}
