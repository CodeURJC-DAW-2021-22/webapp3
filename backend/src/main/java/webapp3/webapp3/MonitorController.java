package webapp3.webapp3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonitorController {

    @GetMapping("/monitor")
    public String monitorWeb(Model model){
        return "USRMON_01Schedule";
    }
}
