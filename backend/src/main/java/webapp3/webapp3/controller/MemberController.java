package webapp3.webapp3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.model.Member;
import webapp3.webapp3.service.ActivityService;
import webapp3.webapp3.service.ExerciseService;
import webapp3.webapp3.service.MemberService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Controller
public class MemberController {

    @Autowired
    private ExerciseService exerServ;

    @Autowired
    private MemberService memServ;

    @Autowired
    private ActivityService actServ;

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
        List<Activity> all = actServ.findAll();
        model.addAttribute("activities", all);
        return "USRMEM_04Activities";
    }

    @GetMapping("/activity/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        Optional<Activity> optAct = actServ.findById(id);

        if (optAct.isPresent()){
            Activity activity = optAct.get();
            if (activity.getImageFile() != null){
                Resource file = new InputStreamResource(activity.getImageFile().getBinaryStream());

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(activity.getImageFile().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
