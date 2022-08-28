package pl.university.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.university.project.odata.CampaignData;
import pl.university.project.services.impl.DefaultCampaignService;
import pl.university.project.services.impl.DefaultClientCampaignService;
import pl.university.project.services.impl.DefaultClientService;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {

    @Resource
    private DefaultCampaignService defaultCampaignService;

    @Resource
    private DefaultClientService defaultClientService;

    @Resource
    private DefaultClientCampaignService defaultClientCampaignService;

    @GetMapping
    public String getAlCampaigns(Model model) {
        model.addAttribute("campaigns", defaultCampaignService.getAllObjects());
        return "campaigns";
    }

    @GetMapping("/{campaignId}")
    public String getCampaignById(@PathVariable Long campaignId, Model model) {
        CampaignData campaignData = defaultCampaignService.getObjectById(campaignId);
        if (campaignData == null || campaignData.getId() == null) {
            return "notFound";
        }
        model.addAttribute("canAddClients", defaultClientService.hasAnyAvailableClientsForCampaign(
                defaultCampaignService.getClientIsForCampaignId(campaignId)));
        model.addAttribute("clientCampaigns", defaultClientCampaignService
                .getAllClientsCampaignsByCampaignID(campaignId));
        model.addAttribute("campaign", defaultCampaignService.getObjectById(campaignId));
        return "campaign";
    }

    @GetMapping(value = "/add")
    public String addCampaign(Model model,@RequestHeader(value = "referer", required = false) final String referer) {
//        setCampaignControllerAllCategories(model);
        model.addAttribute("referer", referer);
        model.addAttribute("campaign", new CampaignData());
        return "saveCampaign";
    }


    @PostMapping(value = "/add")
    public String addCampaign(@Valid @ModelAttribute("campaign") CampaignData campaignData, BindingResult result,
                              Model model, @ModelAttribute("referer") String referer) {
        if (result.hasErrors()) {
//            setCampaignControllerAllCategories(model);
            return "saveCampaign";
        }
        return "redirect:/campaigns/" + defaultCampaignService.saveObject(campaignData);
    }

    @GetMapping(value = "/{campaignId}/update")
    public String updateCampaign(@PathVariable Long campaignId, Model model,
                                 @RequestHeader(value = "referer", required = false) final String referer) {
//        setCampaignControllerAllCategories(model);
        model.addAttribute("referer", referer);
        CampaignData campaignData = defaultCampaignService.getObjectById(campaignId);
        if (campaignData == null) {
            return "notFound";
        }
        model.addAttribute("campaign", defaultCampaignService.getObjectById(campaignId));
        return "saveCampaign";
    }

    @PutMapping("/{campaignId}/update")
    public String updateCampaign(@PathVariable Long campaignId, @Valid @ModelAttribute("campaign") CampaignData campaignData,
                                 BindingResult result, Model model, @ModelAttribute("referer") String referer) {
        campaignData.setId(campaignId);
        if (result.hasErrors()) {
//            setCampaignControllerAllCategories(model);
            return "saveCampaign";
        }
        return "redirect:/campaigns/" + defaultCampaignService.updateObject(campaignData);
    }

    @DeleteMapping("/{campaignId}/delete")
    public String deleteCampaign(@PathVariable Long campaignId) {
        defaultCampaignService.deleteObject(campaignId);
        return "redirect:/campaigns";
    }
}
