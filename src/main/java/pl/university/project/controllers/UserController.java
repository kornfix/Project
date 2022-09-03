package pl.university.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.university.project.odata.UserData;
import pl.university.project.services.impl.DefaultUserService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    @Resource
    private DefaultUserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllObjects().stream()
                .filter(userData -> !userData.getRole().equals("Admin")).collect(Collectors.toList()));
        return "users";
    }

    @GetMapping(value = "/add")
    public String addUser(Model model, @RequestHeader(value = "referer", required = false) final String referer) {
        model.addAttribute("referer", referer);
        model.addAttribute("mod", "CREATE_NEW");
        model.addAttribute("user", new UserData());
        return "saveUser";
    }


    @PostMapping(value = "/add")
    public String addClient(@Valid @ModelAttribute("user") UserData userData, BindingResult result, Model model,
                            @ModelAttribute("referer") String referer) {
        if (!result.hasErrors() && !userData.getPassword().equals(userData.getRepeatPassword())) {
            result.addError(new ObjectError("globalError", "Podane hasła muszą być takie same!"));
        }
        if (!result.hasErrors() && userService.isUsernameUsed(userData.getUsername())) {
            result.addError(new ObjectError("globalError", "Nazwa użytkownika już istnieje!"));
        }
        if (result.hasErrors()) {
            return "saveUser";
        }
        userService.saveObject(userData);
        return "redirect:/admin/users/";
    }

//    @PutMapping("/update")
//    public String updateUser(@ModelAttribute("user") UserData userData, Model model) {
//        model.addAttribute("user", userService.saveObject(userData));
//        return "user";
//    }

    @DeleteMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteObject(userId);
        return "redirect:/admin/users";
    }
}
