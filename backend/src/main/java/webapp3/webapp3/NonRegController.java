package webapp3.webapp3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NonRegController {

    @GetMapping("/inicio")
    public String publicWeb(Model model){
        return "USR_01mainPage";
    }
}
