package pl.university.project.populators.impl;

import pl.university.project.models.Forecast;
import pl.university.project.odata.ForecastData;
import pl.university.project.populators.Populator;
import pl.university.project.utils.PropertyUtil;

import java.text.DecimalFormat;


public class DefaultForecastPopulator implements Populator<Forecast, ForecastData> {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00%");

    @Override
    public void populate(Forecast source, ForecastData target) {
        target.setId(source.getId());
        target.setCreationTime(source.getCreationTime());
        target.setContactType(source.getContactType());
        target.setAge(source.getAge());
        target.setJob(source.getJob());
        target.setMartialStatus(source.getMartialStatus());
        target.setEducationLevel(source.getEducationLevel());
        target.setHasDefaultCredit(source.getHasDefaultCredit());
        target.setHasMortgage(source.getHasMortgage());
        target.setHasConsumerCredit(source.getHasConsumerCredit());
        target.setBalance(source.getBalance());
        target.setCallDurationInSeconds(PropertyUtil.getDurationStringFormat(source.getCallDurationInSeconds()));
        target.setNumberOfContactsDuringCampaign(source.getNumberOfContactsDuringCampaign());
        target.setNumberOfContactsDuringPreviousCampaign(source.getNumberOfContactsDuringPreviousCampaign());
        target.setLastContactDate(source.getLastContactDate());
        target.setLastContactDateFromPreviousCampaign(source.getLastContactDateFromPreviousCampaign());
        target.setPreviousCampaignOutcome(source.getPreviousCampaignOutcome());
        target.setForecastOutcome(source.getForecastOutcome());
        target.setForecastProbability(decimalFormat.format(source.getForecastProbability()));
        target.setCampaignTitle(source.getClientCampaign().getCampaign().getTitle());
        target.setClientFullName(source.getClientCampaign().getClient().getFirstName() + " " +
                source.getClientCampaign().getClient().getLastName());
    }
}
