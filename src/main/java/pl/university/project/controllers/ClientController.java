package pl.university.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.university.project.odata.ClientData;
import pl.university.project.services.impl.DefaultClientService;

import javax.annotation.Resource;

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
        model.addAttribute("client", defaultClientService.getObjectById(clientId));
        return "client";
    }

    @GetMapping(value = "/add")
    public String addClient(Model model) {
        model.addAttribute("client", new ClientData());
        return "saveClient";
    }


    @PostMapping(value = "/add")
    public String addClient(@ModelAttribute("client") ClientData clientData, Model model) {
        model.addAttribute("client", defaultClientService.saveObject(clientData));
        return "client";
    }

    @GetMapping(value = "/{clientId}/update")
    public String updateClient(@PathVariable Long clientId, Model model) {
        model.addAttribute("client", defaultClientService.getObjectById(clientId));
        return "saveClient";
    }

    @PostMapping("/{clientId}/update")
    public String updateClient(@PathVariable Long clientId, @ModelAttribute("client") ClientData clientData, Model model) {
        clientData.setId(clientId);
        model.addAttribute("client", defaultClientService.updateObject(clientData));
        return "client";
    }

    @DeleteMapping("/{clientId}/delete")
    public String deleteClient(@PathVariable Long clientId) {
        defaultClientService.deleteObject(clientId);
        return "clients";
    }
}
