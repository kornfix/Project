package pl.university.project.populators.impl;

import org.springframework.stereotype.Component;
import pl.university.project.models.Forecast;
import pl.university.project.populators.Populator;

import java.util.Calendar;
import java.util.HashMap;

@Component("modelValuesPopulator")
public class DefaultModelDictionaryPopulator implements Populator<Forecast, HashMap<String,Double>> {
    @Override
    public void populate(Forecast source, HashMap<String, Double> target) {
        target.put("age", Double.valueOf(source.getAge()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source.getLastContactDate());
        target.put("day_of_year", Double.valueOf(calendar.get(Calendar.DAY_OF_YEAR)));
        target.put("log_duration", Math.log(source.getCallDurationInSeconds()));
        target.put("log_campaign", Math.log(source.getNumberOfContactsDuringCampaign()));
        target.put("log_pdays", Math.log(source.getNumberOfContactsDuringPreviousCampaign()));
        target.put("job_Pokojówka", source.getJob().equals("Pokojówka")? 1d : 0d);
        target.put("job_Przedsiębiorca", source.getJob().equals("Przedsiębiorca")? 1d : 0d);
        target.put("job_Samozatrudniony", source.getJob().equals("Samozatrudniony")? 1d : 0d);
        target.put("education_Wykształcenie średnie", source.getEducationLevel().equals("Wykształcenie średnie")? 1d : 0d);
        target.put("default_Tak", source.getDefaultCreditStatus()? 1d : 0d);
        target.put("housing_Tak", source.getHasMortgage()? 1d : 0d);
        target.put("contact_Telefoniczny", source.getContactType().equals("Telefoniczny")? 1d : 0d);
        target.put("poutcome_Porażka", source.getPreviousCampaignForecast().equals("Porażka")? 1d : 0d);
    }
}