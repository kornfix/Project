package pl.university.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.university.project.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
