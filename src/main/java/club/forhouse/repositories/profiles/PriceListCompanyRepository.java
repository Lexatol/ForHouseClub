package club.forhouse.repositories.profiles;

import club.forhouse.entities.profiles.PriceListCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListCompanyRepository extends JpaRepository<PriceListCompany, Long> {
}
