package pl.university.project.utils;

import org.pmml4s.model.Model;

import java.util.Arrays;
import java.util.Map;

public final class ForecastUtil {

    private static final Model model = Model.fromInputStream(ForecastUtil.class.getClassLoader().getResourceAsStream("model.pmml"));


    private ForecastUtil() {
    }

    public static Double getProbability(Map<String, Double> values) {
        Object[] valuesMap = Arrays.stream(model.inputNames())
                .map(values::get)
                .toArray();

        Object[] result = model.predict(valuesMap);
        return (Double) result[1];
    }

    public static String getPrediction(Map<String, Double> values) {
        Object[] valuesMap = Arrays.stream(model.inputNames())
                .map(values::get)
                .toArray();

        Object[] result =  model.predict(valuesMap);
        return  Double.compare((Double) result[1], (Double) result[0])>0? "Założy lokatę" : "Niezałoży lokaty";
    }

}
