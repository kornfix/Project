package pl.university.project.utils;

import org.springframework.ui.Model;

public final class ModelUtil {

    private ModelUtil() {
    }

    public static void setClientControllerAllCategories(Model model) {
        model.addAttribute("jobs", PropertyUtil.getJobCategories());
        model.addAttribute("martialStatuses", PropertyUtil.getMartialStatusCategories());
        model.addAttribute("educationLevels", PropertyUtil.getEducationLevelCategories());
        model.addAttribute("contactTypes", PropertyUtil.getContactTypeCategories());
    }
}
