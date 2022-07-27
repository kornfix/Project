package pl.university.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.university.models.User;
import pl.university.project.repositories.UserRepository;

import java.util.List;

@RestController
public class UserController {



    @GetMapping("/users")
    public List<User> getAllUsers() {
    }

    @GetMapping("/users/{id}")
    public List<User> getUserByID(@PathVariable String id) {
        int userId = Integer.parseInt(id);
    }
}
