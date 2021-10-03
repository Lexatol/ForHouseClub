package club.forhouse.repositories;

import club.forhouse.entities.ProfileCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileContractorRepository extends JpaRepository<ProfileCompany, Long> {
}
