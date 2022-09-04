package pl.university.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.university.project.models.ClientCampaignId;
import pl.university.project.odata.CampaignData;
import pl.university.project.odata.ClientCampaignData;
import pl.university.project.odata.ClientData;
import pl.university.project.odata.ForecastData;
import pl.university.project.services.impl.DefaultCampaignService;
import pl.university.project.services.impl.DefaultClientCampaignService;
import pl.university.project.services.impl.DefaultClientService;
import pl.university.project.utils.PropertyUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/campaigns/{campaignId}/participants")
public class ClientCampaignController {

    @Resource
    private DefaultClientCampaignService defaultClientCampaignService;

    @Resource
    private DefaultClientService defaultClientService;

    @Resource
    private DefaultCampaignService defaultCampaignService;

    @GetMapping("/{clientId}")
    public String getClientCampaignById(@PathVariable Long campaignId, @PathVariable Long clientId, Model model) {
        ClientCampaignId clientCampaignId = new ClientCampaignId(campaignId, clientId);
        ClientCampaignData clientCampaignData = defaultClientCampaignService.getObjectById(clientCampaignId);
        if (clientCampaignData == null || clientCampaignData.getClientCampaignId() == null) {
            return "notFound";
        }
        model.addAttribute("clientCampaign", clientCampaignData);
        model.addAttribute("forecasts", defaultClientCampaignService.getAllForecasts(clientCampaignId)
                .stream().sorted(Comparator.comparing(ForecastData::getCreationTime).reversed())
                .collect(Collectors.toList()));
        return "clientCampaign";
    }

    @GetMapping(value = "/add")
    public String addClientCampaign(@PathVariable Long campaignId, Model model,
                                    @RequestHeader(value = "referer", required = false) final String referer) {
        CampaignData thisCampaignData = defaultCampaignService.getObjectById(campaignId);
        ClientCampaignId clientCampaignId = new ClientCampaignId();
        clientCampaignId.setCampaignId(thisCampaignData.getId());
        model.addAttribute("outcomes", PropertyUtil.getOutcomesCategories());
        model.addAttribute("referer", referer);
        Collection<ClientData> availableClients = defaultClientService.filterOutClientsByIds(defaultCampaignService
                .getClientIsForCampaignId(campaignId));
        if (availableClients.isEmpty()) {
            return "redirect:/campaigns/" + campaignId;
        }
        setCampaignClients(model, availableClients);
        ClientCampaignData clientCampaignData = new ClientCampaignData();
        clientCampaignData.setCampaign(thisCampaignData);
        clientCampaignData.setClientCampaignId(clientCampaignId);
        model.addAttribute("clientCampaign", clientCampaignData);
        return "saveClientCampaign";
    }


    @PostMapping(value = "/add")
    public String addClientCampaign(@PathVariable Long campaignId,
                                    @Valid @ModelAttribute("clientCampaign") ClientCampaignData clientCampaignData,
                                    BindingResult result, Model model, @ModelAttribute("referer") String referer) {
        clientCampaignData.getClientCampaignId().setCampaignId(campaignId);
        clientCampaignData.setCampaign(defaultCampaignService.getObjectById(campaignId));
        setCampaignClients(model, campaignId);
        ClientData clientData = defaultClientService.getObjectById(clientCampaignData.getClientCampaignId().getClientId());
        clientCampaignData.setClient(clientData);
        model.addAttribute("clientCampaign", clientCampaignData);
        if (result.hasErrors()) {
            model.addAttribute("outcomes", PropertyUtil.getOutcomesCategories());
            clientCampaignData.getClientCampaignId().setClientId(null);
            return "saveClientCampaign";
        }
        defaultClientCampaignService.saveObject(clientCampaignData);
        return "redirect:/campaigns/" + clientCampaignData.getClientCampaignId().getCampaignId() +
                "/participants/" + clientCampaignData.getClientCampaignId().getClientId();
    }

    @GetMapping(value = "/{clientId}/update")
    public String updateClientCampaign(@PathVariable Long campaignId, @PathVariable Long clientId, Model model,
                                       @RequestHeader(value = "referer", required = false) final String referer) {
        model.addAttribute("outcomes", PropertyUtil.getOutcomesCategories());
        model.addAttribute("referer", referer);
        setCampaignClients(model, campaignId);
        ClientCampaignId clientCampaignId = new ClientCampaignId(campaignId, clientId);
        ClientCampaignData clientCampaignData = defaultClientCampaignService.getObjectById(clientCampaignId);
        if (clientCampaignData == null) {
            return "notFound";
        }
        model.addAttribute("clientCampaign", clientCampaignData);
        return "saveClientCampaign";
    }

    @PutMapping("/{clientId}/update")
    public String updateClientCampaign(@PathVariable Long campaignId, @PathVariable Long clientId,
                                       @Valid @ModelAttribute("clientCampaign") ClientCampaignData clientCampaignData
            , BindingResult result, Model model, @ModelAttribute("referer") String referer) {
        setCampaignClients(model, campaignId);
        clientCampaignData.setClientCampaignId(new ClientCampaignId(campaignId, clientId));
        if (result.hasErrors()) {
            model.addAttribute("outcomes", PropertyUtil.getOutcomesCategories());
            return "saveClientCampaign";
        }
        defaultClientCampaignService.updateObject(clientCampaignData);
        return "redirect:/campaigns/" + clientCampaignData.getClientCampaignId().getCampaignId() +
                "/participants/" + clientCampaignData.getClientCampaignId().getClientId();
    }

//    @PutMapping("/{clientId}/changeNumberOfContacts")
//    public String updateClientCampaign(@PathVariable Long campaignId, @PathVariable Long clientId, @RequestParam Long valueToAppend,
//                                       @ModelAttribute("referer") String referer) {
//        ClientCampaignId clientCampaignId = new ClientCampaignId(campaignId, clientId);
//        defaultClientCampaignService.changeNumberOfContactsDuringCampaign(clientCampaignId, valueToAppend);
//        return "redirect:" + referer;
//    }

    @DeleteMapping("/{clientId}/delete")
    public String deleteClientCampaign(@PathVariable Long campaignId, @PathVariable Long clientId) {
        defaultClientCampaignService.deleteObject(new ClientCampaignId(campaignId, clientId));
        return "redirect:/campaigns/" + campaignId;
    }


    private void setCampaignClients(Model model, Collection<ClientData> clientDataCollection) {
        model.addAttribute("clients", clientDataCollection);
    }

    private void setCampaignClients(Model model, Long campaignId) {
        setCampaignClients(model, defaultClientService.filterOutClientsByIds(
               defaultCampaignService.getClientIsForCampaignId(campaignId)));
    }
}
