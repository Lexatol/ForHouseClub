package club.forhouse.repositories.material;

import club.forhouse.entities.material.MaterialCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialCategoryRepository extends JpaRepository<MaterialCategory, Long> {
}
