package pl.university.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.university.project.models.ClientCampaignId;
import pl.university.project.odata.ForecastData;
import pl.university.project.services.impl.DefaultCampaignService;
import pl.university.project.services.impl.DefaultClientCampaignService;
import pl.university.project.services.impl.DefaultClientService;
import pl.university.project.services.impl.DefaultForecastService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/campaigns/{campaignId}/participants/{clientId}/forecasts")
public class ForecastController {

    @Resource
    private DefaultClientCampaignService defaultClientCampaignService;

    @Resource
    private DefaultClientService defaultClientService;

    @Resource
    private DefaultCampaignService defaultCampaignService;

    @Resource
    private DefaultForecastService defaultForecastService;


//    @GetMapping
//    public String getAllForecasts(@PathVariable Long campaignId, @PathVariable Long clientId, Model model) {
//        ClientCampaignId clientCampaignId = new ClientCampaignId(campaignId, clientId);
//        ClientCampaignData clientCampaignData = defaultClientCampaignService.getObjectById(clientCampaignId);
//        if (clientCampaignData == null || clientCampaignData.getClientCampaignId() == null) {
//            return "notFound";
//        }
//        model.addAttribute("clientCampaign", defaultClientCampaignService.getObjectById(clientCampaignId));
//        model.addAttribute("forecasts", defaultClientCampaignService.getAllForecasts(clientCampaignId)
//                .stream().sorted(Comparator.comparing(ForecastData::getCreationTime)));
//        return "forecasts";
//    }


    @GetMapping("/{forecastId}")
    public String getForecast(@PathVariable Long campaignId, @PathVariable Long clientId,
                              @PathVariable Long forecastId, Model model) {
        ForecastData forecastData = defaultForecastService.getObjectById(forecastId);
        if (forecastData == null || forecastData.getId() == null) {
            return "notFound";
        }
        model.addAttribute("forecast",forecastData);
        model.addAttribute("campaignId",campaignId);
        model.addAttribute("clientId",clientId);
        return "forecast";
    }

    @PostMapping(value = "/add")
    public String add(@PathVariable Long campaignId, @PathVariable Long clientId) {
        ClientCampaignId clientCampaignId = new ClientCampaignId();
        clientCampaignId.setCampaignId(campaignId);
        clientCampaignId.setClientId(clientId);
        defaultClientCampaignService.createNewForecast(clientCampaignId);
        return "redirect:/campaigns/" + campaignId + "/participants/" + clientId;
    }

    @DeleteMapping("/{forecastId}/delete")
    public String deleteClientCampaign(@PathVariable Long campaignId, @PathVariable Long clientId, @PathVariable Long forecastId) {
        defaultForecastService.deleteForecast(forecastId);
        return "redirect:/campaigns/" + campaignId + "/participants/" + clientId;
    }


}
