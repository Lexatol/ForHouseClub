package club.forhouse.repositories.profiles;

import club.forhouse.entities.profiles.LkUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LkUserRepository extends JpaRepository<LkUser, Long> {
}
