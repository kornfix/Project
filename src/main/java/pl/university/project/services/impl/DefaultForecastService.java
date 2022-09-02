package pl.university.project.services.impl;

import org.springframework.stereotype.Service;
import pl.university.project.converters.impl.ForecastConverter;
import pl.university.project.converters.impl.ForecastReversConverter;
import pl.university.project.models.ClientCampaign;
import pl.university.project.models.Forecast;
import pl.university.project.odata.ForecastData;
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
    private ForecastReversConverter forecastReversConverter;

    @Resource
    private ForecastConverter forecastConverter;


    public ForecastData getObjectById(Long id) {
        Forecast forecast = getForecastById(id);
        if (forecast == null) {
            return null;
        }
        return forecastConverter.convert(forecast);
    }

    @Resource
    private DefaultModelDictionaryPopulator modelValuesPopulator;
    public void createNewForecast(ClientCampaign clientCampaign) {
        Forecast forecast = new Forecast();
        forecastReversConverter.convert(clientCampaign, forecast);
        HashMap<String,Double> modelValues = new HashMap<>();
        modelValuesPopulator.populate(forecast,modelValues);
        forecast.setForecastProbability(ForecastUtil.getProbability(modelValues));
        forecast.setForecastOutcome(ForecastUtil.getPrediction(modelValues));
        forecast.setClientCampaign(clientCampaign);
        forecastRepository.saveAndFlush(forecast);
    }

    public void deleteForecast(Long id) {
        forecastRepository.deleteById(id);
    }

    private Forecast getForecastById(Long id) {
        return forecastRepository.findById(id).orElse(null);
    }

}
