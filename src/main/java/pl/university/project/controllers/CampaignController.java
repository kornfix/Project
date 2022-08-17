package pl.university.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.university.project.odata.CampaignData;
import pl.university.project.services.impl.DefaultCampaignService;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/campaigns")
public class CampaignController {


    @Resource
    private DefaultCampaignService defaultCampaignService;

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
        model.addAttribute("campaign", defaultCampaignService.getObjectById(campaignId));
        return "campaign";
    }

    @GetMapping(value = "/add")
    public String addCampaign(Model model) {
//        setCampaignControllerAllCategories(model);
        model.addAttribute("Campaign", new CampaignData());
        return "saveCampaign";
    }


    @PostMapping(value = "/add")
    public String addCampaign(@Valid @ModelAttribute("Campaign") CampaignData campaignData, BindingResult result, Model model) {
        if (result.hasErrors()) {
//            setCampaignControllerAllCategories(model);
            return "saveCampaign";
        }
        return "redirect:/campaigns/" + defaultCampaignService.saveObject(campaignData);
    }

    @GetMapping(value = "/{campaignId}/update")
    public String updateCampaign(@PathVariable Long campaignId, Model model) {
//        setCampaignControllerAllCategories(model);
        CampaignData campaignData = defaultCampaignService.getObjectById(campaignId);
        if (campaignData == null) {
            return "notFound";
        }
        model.addAttribute("campaign", defaultCampaignService.getObjectById(campaignId));
        return "saveCampaign";
    }

    @PutMapping("/{campaignId}/update")
    public String updateCampaign(@PathVariable Long campaignId, @ModelAttribute("campaign") CampaignData campaignData,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
//            setCampaignControllerAllCategories(model);
            return "saveCampaign";
        }
        campaignData.setId(campaignId);
        return "redirect:/Campaigns/" + defaultCampaignService.updateObject(campaignData);
    }

    @DeleteMapping("/{CampaignId}/delete")
    public String deleteCampaign(@PathVariable Long campaignId) {
        defaultCampaignService.deleteObject(campaignId);
        return "redirect:/Campaigns";
    }
}
