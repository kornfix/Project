package pl.university.project.controllers;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.university.project.odata.UserData;
import pl.university.project.services.impl.DefaultUserService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private static final String ADMIN_USERS_URL = "redirect:/admin/users";

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
        Boolean hasErrors = result.hasErrors();
        if (!hasErrors && userService.isUsernameUsed(userData.getNewUsername())) {
            hasErrors = true;
            result.addError(new ObjectError("globalError", "Nazwa użytkownika już istnieje!"));
        }
        if (!hasErrors && !userData.getPassword().equals(userData.getRepeatPassword())) {
            hasErrors = true;
            result.addError(new ObjectError("globalError", "Podane hasła muszą być takie same!"));
        }

        if (hasErrors) {
            model.addAttribute("referer", referer);
            model.addAttribute("mod", "CREATE_NEW");
            model.addAttribute("user", userData);
            return "saveUser";
        }
        userService.saveObject(userData);
        return "redirect:/admin/users/";
    }

    @GetMapping(value = "/{userId}/changeUsername")
    public String changeUsername(@PathVariable Long userId, Model model,
                                 @RequestHeader(value = "referer", required = false) final String referer) {
        model.addAttribute("referer", referer);
        if (userId == null) {
            return "error";
        }
        UserData userData = userService.getObjectById(userId);
        if (userData == null) {
            return "notFound";
        }
        model.addAttribute("user", userData);
        model.addAttribute("mod", "ADMIN_CHANGE_USERNAME");
        return "saveUser";
    }


    @PutMapping("/{userId}/changeUsername")
    public String changeUsername(@PathVariable Long userId, @Valid @ModelAttribute("user") UserData userData, BindingResult result, Model model,
                                 @ModelAttribute("referer") String referer) {
        List<ObjectError> errors = result.getAllErrors().stream().
                filter(e -> !((FieldError) e).getField().toLowerCase(Locale.ROOT).contains("password"))
                .collect(Collectors.toList());
        if (userId == null) {
            return "error";
        }
        userData.setId(userId);
        model.addAttribute("mod", "ADMIN_CHANGE_USERNAME");
        if (CollectionUtils.isEmpty(errors) && userService.isUsernameUsed(userData.getNewUsername())) {
            ObjectError usernameError = new ObjectError("globalError", "Nazwa użytkownika już istnieje!");
            result.addError(usernameError);
            errors.add(usernameError);
        }
        if (CollectionUtils.isNotEmpty(errors)) {
            return "saveUser";
        }
        userService.changeUsername(userData);
        return ADMIN_USERS_URL;
    }


    @GetMapping(value = "/{userId}/changePassword")
    public String changePassword(@PathVariable Long userId, Model model,
                                 @RequestHeader(value = "referer", required = false) final String referer) {
        model.addAttribute("referer", referer);
        if (userId == null) {
            return "error";
        }
        UserData userData = userService.getObjectById(userId);
        if (userData == null) {
            return "notFound";
        }
        model.addAttribute("user", userData);
        model.addAttribute("mod", "ADMIN_CHANGE_PASSWORD");
        return "saveUser";
    }


    @PutMapping("/{userId}/changePassword")
    public String changePassword(@PathVariable Long userId, @Valid @ModelAttribute("user") UserData userData,
                                 BindingResult result, Model model, @ModelAttribute("referer") String referer) {
        List<ObjectError> errors = result.getAllErrors().stream().
                filter(e -> !((FieldError) e).getField().toLowerCase(Locale.ROOT).contains("username"))
                .collect(Collectors.toList());
        if (userId == null) {
            return "error";
        }
        userData.setId(userId);
        model.addAttribute("mod", "ADMIN_CHANGE_PASSWORD");
        if (CollectionUtils.isEmpty(errors) && !userData.getPassword().equals(userData.getRepeatPassword())) {
            ObjectError passwordError = new ObjectError("globalError", "Podane hasła muszą byc takie same!");
            result.addError(passwordError);
            errors.add(passwordError);
        }
        if (CollectionUtils.isNotEmpty(errors)) {
            return "saveUser";
        }
        userService.changePassword(userData);
        return ADMIN_USERS_URL;
    }

    @DeleteMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteObject(userId);
        return ADMIN_USERS_URL;
    }
}
