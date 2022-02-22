package webapp3.webapp3;

import antlr.ASTNULLType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/exercise")
    public String memberPage(Model model){
        return "USRMEM_01ExerciseTable";
    }

    @GetMapping("/editProfile")
    public String editProfile(Model model) {
        return "USRMEM_02EditProfile";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "USRMEM_02Profile";
    }

    @GetMapping("/statistics")
    public String statistics(Model model) {
        return "USRMEM_03Estatistics";
    }

    @GetMapping("/activities")
    public String activities(Model model) {
        return "USRMEM_04Activities";
    }
}
