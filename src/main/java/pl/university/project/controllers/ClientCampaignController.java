package pl.university.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.university.project.models.ClientCampaignId;
import pl.university.project.odata.ClientCampaignData;
import pl.university.project.services.impl.DefaultClientCampaignService;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/campaigns/{campaignId}/")
public class ClientCampaignController {

    @Resource
    private DefaultClientCampaignService defaultClientCampaignService;

    @GetMapping
    public String getAlClientCampaigns(Model model) {
        model.addAttribute("clientCampaigns", defaultClientCampaignService.getAllObjects());
        return "clientCampaigns";
    }

    @GetMapping("/{clientId}")
    public String getClientCampaignById(@PathVariable Long campaignId, @PathVariable Long clientId, Model model) {
        ClientCampaignId clientCampaignId = new ClientCampaignId(campaignId,clientId);
        ClientCampaignData clientCampaignData = defaultClientCampaignService.getObjectById(clientCampaignId);
        if (clientCampaignData == null || clientCampaignData.getClientCampaignId()  == null) {
            return "notFound";
        }
        model.addAttribute("clientCampaign", defaultClientCampaignService.getObjectById(clientCampaignId));
        return "clientCampaign";
    }

    @GetMapping(value = "/add")
    public String addClientCampaign(Model model, @RequestHeader(value = "referer", required = false) final String referer) {
        model.addAttribute("referer", referer);
        model.addAttribute("clientCampaign", new ClientCampaignData());
        return "saveclientCampaign";
    }


    @PostMapping(value = "/add")
    public String addClientCampaign(@PathVariable Long campaignId,
                                    @Valid @ModelAttribute("clientCampaign") ClientCampaignData clientCampaignData,
                                    BindingResult result, Model model, @ModelAttribute("referer") String referer) {
        if (result.hasErrors()) {
//            setclientCampaignControllerAllCategories(model);
            return "saveClientCampaign";
        }
        return "redirect:/clientCampaigns/" + defaultClientCampaignService.saveObject(clientCampaignData);
    }

    @GetMapping(value = "/{clientCampaignId}/update")
    public String updateClientCampaign(@PathVariable Long campaignId, @PathVariable Long clientId, Model model,
                               @RequestHeader(value = "referer", required = false) final String referer) {
//        setclientCampaignControllerAllCategories(model);
        model.addAttribute("referer", referer);
        ClientCampaignId clientCampaignId = new ClientCampaignId(campaignId,clientId);
        ClientCampaignData clientCampaignData = defaultClientCampaignService.getObjectById(clientCampaignId);
        if (clientCampaignData == null) {
            return "notFound";
        }
        model.addAttribute("clientCampaign", defaultClientCampaignService.getObjectById(clientCampaignId));
        return "saveclientCampaign";
    }

    @PutMapping("/{clientCampaignId}/update")
    public String updateClientCampaign(@PathVariable Long campaignId, @PathVariable Long clientId,
                                       @Valid @ModelAttribute("clientCampaign") ClientCampaignData clientCampaignData
            , BindingResult result, Model model, @ModelAttribute("referer") String referer) {
        clientCampaignData.setClientCampaignId(new ClientCampaignId(campaignId,clientId));
        if (result.hasErrors()) {
//            setclientCampaignControllerAllCategories(model);
            return "saveclientCampaign";
        }
        return "redirect:/clientCampaigns/" + defaultClientCampaignService.updateObject(clientCampaignData);
    }

    @DeleteMapping("/{clientCampaignId}/delete")
    public String deleteClientCampaign(@PathVariable Long campaignId, @PathVariable Long clientId) {
        defaultClientCampaignService.deleteObject(new ClientCampaignId(campaignId,clientId));
        return "redirect:/clientCampaigns";
    }
}
