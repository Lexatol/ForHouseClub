package club.forhouse.repositories.estimate;

import club.forhouse.entities.profiles.Company;
import club.forhouse.entities.estimate.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, Long> {
    Optional<Estimate> findTopByCompany(Company company);
}
