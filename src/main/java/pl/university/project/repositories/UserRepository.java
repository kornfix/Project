package pl.university.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.university.project.models.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
