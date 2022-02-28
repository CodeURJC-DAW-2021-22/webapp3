package webapp3.webapp3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.service.ExerciseService;

import java.util.List;


@Controller
public class MemberController {

    @Autowired
    private ExerciseService exerServ;


    @GetMapping("/exercise")
    public String exercise (Model model){
        List<Exercise> all = exerServ.findAll();
        model.addAttribute("exerciseList", all);
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
