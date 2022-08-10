package pl.university.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.university.project.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
