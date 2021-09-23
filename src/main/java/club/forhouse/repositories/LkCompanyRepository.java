package club.forhouse.repositories;

import club.forhouse.entities.LkCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LkCompanyRepository extends JpaRepository<LkCompany, Long> {
//TODO необходимо подготовить SQL запросы на поиск списка поставщиков и подрядчиков


}
