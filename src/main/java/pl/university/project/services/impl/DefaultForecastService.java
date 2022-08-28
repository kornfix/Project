package pl.university.project.services.impl;

import org.springframework.stereotype.Service;
import pl.university.project.models.ClientCampaign;
import pl.university.project.models.Forecast;
import pl.university.project.populators.impl.DefaultForecastPopulator;
import pl.university.project.populators.impl.DefaultModelDictionaryPopulator;
import pl.university.project.repositories.ForecastRepository;
import pl.university.project.utils.ForecastUtil;

import javax.annotation.Resource;
import java.util.HashMap;

@Service("forecastService")
public class DefaultForecastService {

    @Resource
    private ForecastRepository forecastRepository;

    @Resource
    private DefaultForecastPopulator forecastPopulator;

    @Resource
    private DefaultModelDictionaryPopulator modelValuesPopulator;
    public void createNewPrediction(ClientCampaign clientCampaign) {
        Forecast forecast = new Forecast();
        forecastPopulator.populate(clientCampaign, forecast);
        HashMap<String,Double> modelValues = new HashMap<>();
        modelValuesPopulator.populate(forecast,modelValues);
        forecast.setForecastProbability(ForecastUtil.getProbability(modelValues));
        forecast.setForecastOutcome(ForecastUtil.getPrediction(modelValues));
        forecast.setClientCampaign(clientCampaign);
        forecastRepository.saveAndFlush(forecast);
    }

}
