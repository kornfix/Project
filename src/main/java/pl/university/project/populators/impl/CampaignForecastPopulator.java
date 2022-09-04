package pl.university.project.populators.impl;

import org.apache.commons.collections4.CollectionUtils;
import pl.university.project.models.ClientCampaign;
import pl.university.project.models.Forecast;
import pl.university.project.odata.CampaignData;
import pl.university.project.populators.Populator;

import java.text.DecimalFormat;
import java.util.Comparator;

public class CampaignForecastPopulator implements Populator<ClientCampaign, CampaignData> {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00%");

    @Override
    public void populate(ClientCampaign source, CampaignData target) {
        target.setId(source.getClientCampaignId().getCampaignId());
        if(CollectionUtils.isNotEmpty(source.getForecasts())) {
            source.getForecasts().stream().max(Comparator.comparing(Forecast::getCreationTime)).
                    ifPresent(forecast -> target.setNewestForecastProbability(
                            decimalFormat.format(forecast.getForecastProbability())));
        }
        target.setTitle(source.getCampaign().getTitle());
    }
}