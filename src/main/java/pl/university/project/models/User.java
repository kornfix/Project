package pl.university.project.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
}
