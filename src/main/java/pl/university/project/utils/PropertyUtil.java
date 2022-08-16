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

    public static List<String> getJobCategories()
    {
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
}
