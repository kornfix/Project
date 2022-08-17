package pl.university.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.university.project.odata.ClientData;
import pl.university.project.services.impl.DefaultClientService;

import javax.annotation.Resource;
import javax.validation.Valid;

import static pl.university.project.utils.ModelUtil.setClientControllerAllCategories;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Resource
    private DefaultClientService defaultClientService;

    @GetMapping
    public String getAlClients(Model model) {
        model.addAttribute("clients", defaultClientService.getAllObjects());
        return "clients";
    }

    @GetMapping("/{clientId}")
    public String getClientById(@PathVariable Long clientId, Model model) {
        ClientData clientData = defaultClientService.getObjectById(clientId);
        if (clientData == null || clientData.getId() == null) {
            return "notFound";
        }
        model.addAttribute("client", defaultClientService.getObjectById(clientId));
        return "client";
    }

    @GetMapping(value = "/add")
    public String addClient(Model model) {
        setClientControllerAllCategories(model);
        model.addAttribute("client", new ClientData());
        return "saveClient";
    }


    @PostMapping(value = "/add")
    public String addClient(@Valid @ModelAttribute("client") ClientData clientData, BindingResult result, Model model) {
        if (result.hasErrors()) {
            setClientControllerAllCategories(model);
            return "saveClient";
        }
        return "redirect:/clients/" + defaultClientService.saveObject(clientData);
    }

    @GetMapping(value = "/{clientId}/update")
    public String updateClient(@PathVariable Long clientId, Model model) {
        setClientControllerAllCategories(model);
        ClientData clientData = defaultClientService.getObjectById(clientId);
        if (clientData == null) {
            return "notFound";
        }
        model.addAttribute("client", defaultClientService.getObjectById(clientId));
        return "saveClient";
    }

    @PutMapping("/{clientId}/update")
    public String updateClient(@PathVariable Long clientId, @ModelAttribute("client") ClientData clientData,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            setClientControllerAllCategories(model);
            return "saveClient";
        }
        clientData.setId(clientId);
        return "redirect:/clients/" +defaultClientService.updateObject(clientData);
    }

    @DeleteMapping("/{clientId}/delete")
    public String deleteClient(@PathVariable Long clientId) {
        defaultClientService.deleteObject(clientId);
        return "redirect:/clients";
    }
}
