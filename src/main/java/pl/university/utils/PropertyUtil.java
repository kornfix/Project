package pl.university.utils;

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

}
