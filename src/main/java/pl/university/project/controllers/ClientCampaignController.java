package pl.university.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.university.project.models.ClientCampaignId;
import pl.university.project.odata.CampaignData;
import pl.university.project.odata.ClientCampaignData;
import pl.university.project.odata.ClientData;
import pl.university.project.services.impl.DefaultCampaignService;
import pl.university.project.services.impl.DefaultClientCampaignService;
import pl.university.project.services.impl.DefaultClientService;
import pl.university.project.utils.PropertyUtil;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
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

    @GetMapping
    public String getAlClientCampaigns(@PathVariable Long campaignId, Model model) {

        model.addAttribute("canAddClients", getAvailableClientForCampaign().size() != 0);
        model.addAttribute("campaign", defaultCampaignService.getObjectById(campaignId));
        model.addAttribute("clientCampaigns", defaultClientCampaignService.getAllObjects());
        return "clientCampaigns";
    }

    @GetMapping("/{clientId}")
    public String getClientCampaignById(@PathVariable Long campaignId, @PathVariable Long clientId, Model model) {
        ClientCampaignId clientCampaignId = new ClientCampaignId(campaignId, clientId);
        ClientCampaignData clientCampaignData = defaultClientCampaignService.getObjectById(clientCampaignId);
        if (clientCampaignData == null || clientCampaignData.getClientCampaignId() == null) {
            return "notFound";
        }
        model.addAttribute("clientCampaign", defaultClientCampaignService.getObjectById(clientCampaignId));
        return "clientCampaign";
    }

    @GetMapping(value = "/add")
    public String addClientCampaign(@PathVariable Long campaignId, Model model,
                                    @RequestHeader(value = "referer", required = false) final String referer) {
        CampaignData thisCampaignData = defaultCampaignService.getObjectById(campaignId);
        ClientCampaignId clientCampaignId = new ClientCampaignId();
        clientCampaignId.setCampaignId(thisCampaignData.getId());
        model.addAttribute("referer", referer);
        Collection<ClientData> avaiableClients = getAvailableClientForCampaign();
        if (avaiableClients.size() == 0) {
//            model.addAttribute("canAddClients", Boolean.FALSE);
            return "redirect:/campaigns/" + campaignId + "/participants";

        }
        setCampaignClients(model, avaiableClients);
//        model.addAttribute("oldCampaigns", defaultCampaignService.getAllObjects().stream()
//                .filter( campaignData -> PropertyUtil.validateOldCampaign(campaignData,thisCampaignData)).collect(Collectors.toList()));
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
        setCampaignClients(model);
        ClientData clientData = defaultClientService.getObjectById(clientCampaignData.getClientCampaignId().getClientId());
        clientCampaignData.setClient(clientData);
        model.addAttribute("clientCampaign", clientCampaignData);
        if (result.hasErrors()) {
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
        CampaignData thisCampaignData = defaultCampaignService.getObjectById(campaignId);
        model.addAttribute("referer", referer);
        setCampaignClients(model);
//        model.addAttribute("oldCampaigns", defaultCampaignService.getAllObjects().stream()
//                .filter( campaignData -> PropertyUtil.validateOldCampaign(campaignData,thisCampaignData)).collect(Collectors.toList()));
        ClientCampaignId clientCampaignId = new ClientCampaignId(campaignId, clientId);
        ClientCampaignData clientCampaignData = defaultClientCampaignService.getObjectById(clientCampaignId);
        if (clientCampaignData == null) {
            return "notFound";
        }
        model.addAttribute("clientCampaign", defaultClientCampaignService.getObjectById(clientCampaignId));
        return "saveClientCampaign";
    }

    @PutMapping("/{clientId}/update")
    public String updateClientCampaign(@PathVariable Long campaignId, @PathVariable Long clientId,
                                       @Valid @ModelAttribute("clientCampaign") ClientCampaignData clientCampaignData
            , BindingResult result, Model model, @ModelAttribute("referer") String referer) {
        setCampaignClients(model);
        clientCampaignData.setClientCampaignId(new ClientCampaignId(campaignId, clientId));
        if (result.hasErrors()) {
            return "saveClientCampaign";
        }
        defaultClientCampaignService.updateObject(clientCampaignData);
        return "redirect:/campaigns/" + clientCampaignData.getClientCampaignId().getCampaignId() +
                "/participants/" + clientCampaignData.getClientCampaignId().getClientId();
    }

    @PutMapping("/{clientId}/changeNumberOfContacts")
    public String updateClientCampaign(@PathVariable Long campaignId, @PathVariable Long clientId, @RequestParam Long valueToAppend,
                                       @ModelAttribute("referer") String referer) {
        ClientCampaignId clientCampaignId = new ClientCampaignId(campaignId, clientId);
        defaultClientCampaignService.changeNumberOfContactsDuringCampaign(clientCampaignId, valueToAppend);
        return "redirect:" + referer;
    }

    @DeleteMapping("/{clientId}/delete")
    public String deleteClientCampaign(@PathVariable Long campaignId, @PathVariable Long clientId) {
        defaultClientCampaignService.deleteObject(new ClientCampaignId(campaignId, clientId));
        return "redirect:/campaigns/" + campaignId + "/participants";
    }

    private Collection<ClientData> getAvailableClientForCampaign() {
        Collection<Long> campaignsClientIds = defaultClientCampaignService.getAllParticipantsIDs();
        return defaultClientService.getAllObjects().stream()
                .filter(PropertyUtil::validateClient)
                .filter(clientData -> PropertyUtil.clientNotWithIDs(clientData, campaignsClientIds))
                .collect(Collectors.toList());
    }

    private void setCampaignClients(Model model, Collection<ClientData> clientDataCollection) {
        model.addAttribute("clients", clientDataCollection);
    }

    private void setCampaignClients(Model model) {
        setCampaignClients(model, getAvailableClientForCampaign());
    }
}
