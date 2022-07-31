package pl.university.project.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.services.UserService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {


    @Resource
    private UserService userService;

    @GetMapping("/users")
    public List<UserData> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserData getUserByID(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserData addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/users")
    public UserData updateUser(@RequestBody User user) {
        return userService.save(user);
    }
}
