package webapp3.webapp3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/socio")
    public String memberPage(Model model){
        return "USRMEM_01ExerciseTable";
    }
}
