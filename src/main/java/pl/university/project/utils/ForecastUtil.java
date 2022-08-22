package pl.university.project.utils;

import org.pmml4s.model.Model;

public final class ForecastUtil {

    private final Model model = Model.fromFile(ForecastUtil.class.getClassLoader().getResource("model.pmml").getFile());

    private ForecastUtil() {
    }


}
