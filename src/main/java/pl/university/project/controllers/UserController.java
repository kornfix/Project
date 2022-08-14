package pl.university.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.university.project.odata.UserData;
import pl.university.project.services.UserService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/users")
public class UserController {


    @Resource
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserByID(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }


    @GetMapping(value = "/add")
    public String addUser(Model model) {
        model.addAttribute("user", new UserData());
        return "saveUser";
    }


    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user") UserData userData, Model model) {
        model.addAttribute("user", userService.save(userData));
        return "user";
    }

    @PutMapping("/update")
    public String updateUser(@ModelAttribute("user") UserData userData, Model model) {
        model.addAttribute("user", userService.save(userData));
        return "user";
    }
}
