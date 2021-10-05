package club.forhouse.repositories;

import club.forhouse.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserEmailAndUserPassword(String email, String password);

    Optional<User> findUserByUserEmail(String email);

    Optional<User> findUserByUserName(String name);
}
