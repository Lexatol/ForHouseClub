package club.forhouse.repositories;

import club.forhouse.entities.LkUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LkUserRepository extends JpaRepository<LkUser, Long> {
}
