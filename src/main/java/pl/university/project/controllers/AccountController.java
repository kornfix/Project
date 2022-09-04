package pl.university.project.controllers;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.university.project.details.MyUserDetails;
import pl.university.project.odata.UserData;
import pl.university.project.services.impl.DefaultUserService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    private DefaultUserService userService;


    @GetMapping
    public String getUser(Model model) {
        Long userId = getCurrentUserId();
        if (userId == null) {
            return "error";
        }
        UserData userData = userService.getObjectById(userId);
        if (userData == null || userData.getId() == null) {
            return "notFound";
        }
        model.addAttribute("user", userData);
        return "user";
    }


    @GetMapping(value = "/changeUsername")
    public String changeUsername(Model model,
                                 @RequestHeader(value = "referer", required = false) final String referer) {
        model.addAttribute("referer", referer);
        Long userId = getCurrentUserId();
        if (userId == null) {
            return "error";
        }
        UserData userData = userService.getObjectById(userId);
        if (userData == null) {
            return "notFound";
        }
        model.addAttribute("user", userData);
        model.addAttribute("mod", "CHANGE_USERNAME");
        return "saveUser";
    }


    @PutMapping("/changeUsername")
    public String changeUsername(@Valid @ModelAttribute("user") UserData userData, BindingResult result, Model model,
                                 @ModelAttribute("referer") String referer) {
        List<ObjectError> errors = result.getAllErrors().stream().
                filter(e -> !((FieldError) e).getField().toLowerCase(Locale.ROOT).contains("password"))
                .collect(Collectors.toList());
        Long userId = getCurrentUserId();
        if (userId == null) {
            return "error";
        }
        userData.setId(userId);
        model.addAttribute("mod", "CHANGE_USERNAME");
        if (CollectionUtils.isEmpty(errors) && userService.isUsernameUsed(userData.getNewUsername())) {
            ObjectError usernameError = new ObjectError("globalError", "Nazwa użytkownika już istnieje!");
            result.addError(usernameError);
            errors.add(usernameError);
        }
        if (CollectionUtils.isNotEmpty(errors)) {
            return "saveUser";
        }
        userService.changeUsername(userData);
        return "redirect:/account";
    }


    @GetMapping(value = "/changePassword")
    public String changePassword(Model model,
                                 @RequestHeader(value = "referer", required = false) final String referer) {
        model.addAttribute("referer", referer);
        Long userId = getCurrentUserId();
        if (userId == null) {
            return "error";
        }
        UserData userData = userService.getObjectById(userId);
        if (userData == null) {
            return "notFound";
        }
        model.addAttribute("user", userData);
        model.addAttribute("mod", "CHANGE_PASSWORD");
        return "saveUser";
    }


    @PutMapping("/changePassword")
    public String changePassword(@Valid @ModelAttribute("user") UserData userData, BindingResult result, Model model,
                                 @ModelAttribute("referer") String referer) {
        List<ObjectError> errors = result.getAllErrors().stream().
                filter(e -> !((FieldError) e).getField().toLowerCase(Locale.ROOT).contains("username"))
                .collect(Collectors.toList());
        Long userId = getCurrentUserId();
        if (userId == null) {
            return "error";
        }
        userData.setId(userId);
        model.addAttribute("mod", "CHANGE_PASSWORD");
        if (CollectionUtils.isEmpty(errors) && !userData.getPassword().equals(userData.getRepeatPassword())) {
            ObjectError passwordError = new ObjectError("globalError", "Podane hasła muszą byc takie same!");
            result.addError(passwordError);
            errors.add(passwordError);
        }
        if (CollectionUtils.isNotEmpty(errors)) {
            return "saveUser";
        }
        userService.changePassword(userData);
        return "redirect:/account";
    }

    private Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principal instanceof MyUserDetails)) {
            return null;
        }
        return ((MyUserDetails) principal).getUserId();
    }
}
