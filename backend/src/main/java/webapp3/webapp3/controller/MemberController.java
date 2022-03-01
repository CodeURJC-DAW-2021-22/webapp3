package webapp3.webapp3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.model.Member;
import webapp3.webapp3.service.ExerciseService;
import webapp3.webapp3.service.MemberService;

import java.util.List;
import java.util.Optional;


@Controller
public class MemberController {

    @Autowired
    private ExerciseService exerServ;

    @Autowired
    private MemberService memServ;


    @GetMapping("/exercise")
    public String exercise (Model model){
        List<Exercise> all = exerServ.findAll();
        model.addAttribute("exercises", all);
        return "USRMEM_01ExerciseTable";
    }

    @GetMapping("/editProfile")
    public String editProfile(Model model) {
        return "USRMEM_02EditProfile";
    }

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable long id) {
        Optional<Member> mem = memServ.findById(id);
        if(mem.isPresent()){
            model.addAttribute("member", mem.get());
            return "USRMEM_02Profile";
        }
        return "404";
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
