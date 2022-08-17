package pl.university.project.utils;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public final class ModelUtil {

    private ModelUtil() {
    }

    public static void setClientControllerAllCategories(Model model) {
        model.addAttribute("jobs", PropertyUtil.getJobCategories());
        model.addAttribute("martialStatuses", PropertyUtil.getMartialStatusCategories());
        model.addAttribute("educationLevels", PropertyUtil.getEducationLevelCategories());
        model.addAttribute("contactTypes", PropertyUtil.getContactTypeCategories());
    }

    public static Optional<String> getPreviousPageByRequest(HttpServletRequest request)
    {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }
}
