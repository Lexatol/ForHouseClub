package club.forhouse.repositories.estimate;

import club.forhouse.entities.estimate.Estimate;
import club.forhouse.entities.profiles.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, Long> {
    List<Estimate> findTop1ByCompany(Company company, Pageable pageable);

    Page<Estimate> findAllByCompany(Company company, Pageable pageable);
}
