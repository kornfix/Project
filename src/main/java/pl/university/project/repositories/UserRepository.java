package pl.university.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.university.project.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
