package club.forhouse.repositories.tenders;

import club.forhouse.entities.profiles.Company;
import club.forhouse.entities.tenders.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {
    Tender findByTitle(String title);

    List<Tender> findAllByCustomer(Company customer);

    List<Tender> findAllByContractor(Company company);
}
