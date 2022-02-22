package webapp3.webapp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private ActivityRepository act;

    @GetMapping("/adminestadisticas")
    public String stadistics(Model model){
        return "USRADM_01Statistics";
    }

    @GetMapping("/adminactividades")
    public String activities(Model model){
        return "USRADM_02ActivitiesList";
    }

    @GetMapping("/adminmonitores")
    public String monitors(Model model){
        return "USRADM_03MonitorsList";
    }

    @GetMapping("/adminclientes")
    public String clients(Model model){
        return "USRADM_12Clients";
    }
}
