package pl.university.project.populators.impl;

import org.springframework.stereotype.Component;
import pl.university.project.models.ClientCampaign;
import pl.university.project.models.Forecast;
import pl.university.project.populators.Populator;
import pl.university.project.utils.PropertyUtil;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;

@Component("forecastPopulator")
public class DefaultForecastPopulator implements Populator<ClientCampaign, Forecast> {
    @Override
    public void populate(ClientCampaign source, Forecast target) {
        if (target.getId() == null) {
            target.setCreationTime(Timestamp.from(Instant.now()));
        }
        target.setContactType(source.getClient().getContactType());
        target.setAge(source.getClient().getAge());
        target.setJob(source.getClient().getJob());
        target.setMartialStatus(source.getClient().getMartialStatus());
        target.setEducationLevel(source.getClient().getEducationLevel());
        target.setDefaultCreditStatus(source.getClient().getDefaultCreditStatus());
        target.setHasMortgage(source.getClient().getHasMortgage());
        target.setHasConsumerCredit(source.getClient().getHasConsumerCredit());
        target.setBalance(source.getClient().getBalance());
        target.setCallDurationInSeconds(source.getCallDurationInSeconds());
        target.setNumberOfContactsDuringCampaign(source.getNumberOfContactsDuringCampaign());
        ClientCampaign previousCampaign = source.getClient().getClientCampaigns().stream()
                .filter(e -> PropertyUtil.validateOldCampaign(e.getCampaign(), source.getCampaign()))
                .sorted(Comparator.comparing(clientCampaign -> clientCampaign.getCampaign()
                        .getCampaignEndDate())).findFirst().orElse(null);
        target.setLastContactDate(source.getLastContactDate());
        if(previousCampaign!=null){
            target.setNumberOfContactsDuringPreviousCampaign(previousCampaign.getNumberOfContactsDuringCampaign());
            target.setLastContactDateFromPreviousCampaign(previousCampaign.getLastContactDate());
            Forecast previousForecast = previousCampaign.getForecasts().stream()
                    .sorted(Comparator.comparing(Forecast::getCreationTime)).findFirst().orElse(null);
            if(previousForecast!=null)
            {
                target.setPreviousCampaignForecast(previousForecast.getForecastOutcome());
            }else {
                target.setPreviousCampaignForecast("Nie wiadomo");
            }
        }else{
            target.setNumberOfContactsDuringPreviousCampaign(0L);
            target.setLastContactDateFromPreviousCampaign(null);
            target.setPreviousCampaignForecast("Nie wiadomo");
        }
    }
}
