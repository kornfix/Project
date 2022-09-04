package pl.university.project.populators.impl;

import org.springframework.stereotype.Component;
import pl.university.project.models.Forecast;
import pl.university.project.populators.Populator;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.HashMap;

@Component("modelValuesPopulator")
public class ModelPopulator implements Populator<Forecast, HashMap<String, Double>> {
    @Override
    public void populate(Forecast source, HashMap<String, Double> target) {
        target.put("age", Double.valueOf(source.getAge()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source.getLastContactDate());
        target.put("day_of_year", (double) calendar.get(Calendar.DAY_OF_YEAR));
        target.put("log_duration", Math.log(source.getCallDurationInSeconds()));
        target.put("log_campaign", Math.log(source.getNumberOfContactsDuringCampaign()));
        if (source.getLastContactDateFromPreviousCampaign() != null) {

            target.put("log_pdays", Math.log(ChronoUnit.DAYS.between(
                    source.getLastContactDateFromPreviousCampaign().toLocalDate(),
                    source.getLastContactDate().toLocalDate())));
        } else {
            target.put("log_pdays", -1D);
        }
        target.put("job_Bezrobotny", source.getJob().equals("Bezrobotny") ? 1d : 0d);
        target.put("job_Pracownik zarządzający", source.getJob().equals("Pracownik na stanowisku kierowniczym") ? 1d : 0d);
        target.put("job_Samozatrudniony", source.getJob().equals("Samozatrudniony") ? 1d : 0d);
        target.put("job_Student", source.getJob().equals("Student") ? 1d : 0d);
        target.put("education_Wykształcenie średnie", source.getEducationLevel().equals("Wykształcenie średnie") ? 1d : 0d);
        target.put("default_Tak", Boolean.TRUE.equals(source.getHasDefaultCredit()) ? 1d : 0d);
        target.put("housing_Tak", Boolean.TRUE.equals(source.getHasMortgage()) ? 1d : 0d);
        target.put("contact_Nie podano", source.getContactType().equals("Nie wiadomo") ? 1d : 0d);
        target.put("contact_Telefoniczny", source.getContactType().equals("Telefoniczny") ? 1d : 0d);
        if(source.getPreviousCampaignOutcome()!=null) {
            target.put("poutcome_Nie podano", "Nie wiadomo".equals(source.getPreviousCampaignOutcome()) ? 1d : 0d);
            target.put("poutcome_Porażka", "Porażka".equals(source.getPreviousCampaignOutcome()) ? 1d : 0d);
        }else{
            target.put("poutcome_Nie podano",1d);
            target.put("poutcome_Porażka", 0d);
        }
    }
}
