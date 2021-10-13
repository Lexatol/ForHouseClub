package club.forhouse.repositories;

import club.forhouse.entities.PriceListCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListCompanyRepository extends JpaRepository<PriceListCompany, Long> {
}
