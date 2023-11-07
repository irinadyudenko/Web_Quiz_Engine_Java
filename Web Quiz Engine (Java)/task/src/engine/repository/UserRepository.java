package engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import engine.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
    boolean existsByEmail(String email);

}
