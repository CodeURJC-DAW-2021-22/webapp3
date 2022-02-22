package webapp3.webapp3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.service.ActivityService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ActivityService actServ;

    @GetMapping("/adminestadisticas")
    public String statistics(Model model){
        return "USRADM_01Statistics";
    }

    @GetMapping("/adminactividades")
    public String activities(Model model){
        List<Activity> all = actServ.findAll();
        model.addAttribute("activitiesList", all);
        return "USRADM_02ActivitiesList";
    }

    @GetMapping("/adminmonitores")
    public String monitors(Model model){
        /*List<Monitor> all = monServ.findAll();
        model.addAttribute("monitorsList", all);*/
        return "USRADM_03MonitorsList";
    }

    @GetMapping("/adminclientes")
    public String clients(Model model){
        /*List<Client> all = usrServ.findAll();
        model.addAttribute("clientList", all);*/
        return "USRADM_12Clients";
    }
}
