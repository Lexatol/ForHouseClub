package club.forhouse.repositories.worktemplate;

import club.forhouse.entities.worktemplate.WorkTemplateMaterial;
import club.forhouse.entities.worktemplate.WorkTemplateOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTemplateMaterialRepository extends JpaRepository<WorkTemplateMaterial, Long> {
    List<WorkTemplateMaterial> findAllByOperationId(WorkTemplateOperation operation);
}
