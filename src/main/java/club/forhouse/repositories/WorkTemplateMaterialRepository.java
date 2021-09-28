package club.forhouse.repositories;

import club.forhouse.entities.WorkTemplate;
import club.forhouse.entities.WorkTemplateMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTemplateMaterialRepository extends JpaRepository<WorkTemplateMaterial, Long> {
    List<WorkTemplateMaterial> findAllByTemplateId(WorkTemplate template);
}
