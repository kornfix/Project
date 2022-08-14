package pl.university.project.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.university.project.models.User;
import pl.university.project.odata.UserData;
import pl.university.project.services.UserService;

import javax.annotation.Resource;
import java.util.Collections;

@Controller
@RequestMapping("/users")
public class UserController {


    @Resource
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        UserData userData = new UserData();
        userData.setId("12");
        userData.setFirstName("Adam");
        userData.setLastName("Nowak");
        model.addAttribute("users", Collections.singleton(userData));
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserByID(@PathVariable String id, Model model) {
        UserData userData = new UserData();
        userData.setId("12");
        userData.setFirstName("Adam");
        userData.setLastName("Nowak");
        model.addAttribute("user", userData);
        return "user";
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
