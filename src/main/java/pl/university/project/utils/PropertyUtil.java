package pl.university.project.utils;

import java.util.ArrayList;
import java.util.List;

public final class PropertyUtil {


    private static final String DIVIDER = ",";

    private PropertyUtil() {
    }

    public static List<String> splitProperty(String value) {
        return splitProperty(value, DIVIDER);
    }

    public static List<String> splitProperty(String value, String divider) {
        return List.of(value.split(divider));
    }

    public static List<String> getJobCategories() {
        List<String> jobs = new ArrayList<>();
        jobs.add("Pracownik na stanowisku kierowniczym");
        jobs.add("Pracownik fizyczny");
        jobs.add("Technik");
        jobs.add("Pracownik administracyjny");
        jobs.add("Pracownik w sektorze usług");
        jobs.add("Emeryt");
        jobs.add("Samozatrudniony");
        jobs.add("Student");
        jobs.add("Bezrobotny");
        jobs.add("Przedsiębiorca");
        jobs.add("Pokojówka");
        jobs.add("Nie wiadomo");
        return jobs;
    }

    public static List<String> getMartialStatusCategories() {
        List<String> martialStatuses = new ArrayList<>();
        martialStatuses.add("Zamężny");
        martialStatuses.add("Niezamężny");
        martialStatuses.add("Rozwiedziony");
        return martialStatuses;
    }

    public static List<String> getEducationLevelCategories() {
        List<String> educationLevels = new ArrayList<>();
        educationLevels.add("Wykształcenie wyższe");
        educationLevels.add("Wykształcenie średnie");
        educationLevels.add("Wykształcenie podstawowe");
        educationLevels.add("Nie wiadomo");
        return educationLevels;
    }

    public static List<String> getContactTypeCategories() {
        List<String> contactTypes = new ArrayList<>();
        contactTypes.add("Komórkowy");
        contactTypes.add("Telefoniczny");
        contactTypes.add("Nie wiadomo");
        return contactTypes;
    }

}
