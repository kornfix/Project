package pl.university.project.populators.impl;

import pl.university.project.converters.impl.CampaignConverter;
import pl.university.project.converters.impl.ClientConverter;
import pl.university.project.models.ClientCampaign;
import pl.university.project.models.Forecast;
import pl.university.project.odata.ClientCampaignData;
import pl.university.project.populators.Populator;
import pl.university.project.utils.PropertyUtil;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Comparator;

public class ClientCampaignPopulator implements Populator<ClientCampaign, ClientCampaignData> {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00%");

    @Resource
    private ClientConverter clientConverter;

    @Resource
    private CampaignConverter campaignConverter;

    @Override
    public void populate(ClientCampaign source, ClientCampaignData target) {
        target.setClientCampaignId(source.getClientCampaignId());
        if (source.getClient() != null) {
            target.setClient(clientConverter.convert(source.getClient()));
        }
        if (source.getCampaign() != null) {
            target.setCampaign(campaignConverter.convert(source.getCampaign()));
        }
        if (source.getCampaignOutcome() != null) {
            target.setCampaignOutcome(source.getCampaignOutcome());
        }
        target.setCallDurationInSeconds(PropertyUtil.getDurationStringFormat(source.getCallDurationInSeconds()));
        target.setLastContactDate(source.getLastContactDate());
        target.setNumberOfContactsDuringCampaign(source.getNumberOfContactsDuringCampaign());
        source.getForecasts()
                    .stream().max(Comparator.comparing(Forecast::getCreationTime)).ifPresent(forecast -> target.setNewestForecastProbability(
                    decimalFormat.format(forecast.getForecastProbability())));
        }
}
