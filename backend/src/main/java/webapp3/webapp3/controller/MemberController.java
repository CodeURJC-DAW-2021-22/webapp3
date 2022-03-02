package webapp3.webapp3.controller;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.DateType;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.model.Member;
import webapp3.webapp3.service.ActivityService;
import webapp3.webapp3.service.ExerciseService;
import webapp3.webapp3.service.MemberService;

import java.io.IOException;
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

    @GetMapping("/exercise/{id}/image")
    public ResponseEntity<Object> downloadExerciseImage(@PathVariable long id) throws SQLException {
        Optional<Exercise> optExer = exerServ.findById(id);

        if (optExer.isPresent()){
            Exercise exercise = optExer.get();
            if (exercise.getImageFile() != null){
                Resource file = new InputStreamResource(exercise.getImageFile().getBinaryStream());

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(exercise.getImageFile().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/exercise/{id}/pdf")
    public void pdfGenerator(){

    }

    @GetMapping("/editProfile")
    public String editProfile(Model model) {
        return "USRMEM_02EditProfile";
    }

    @GetMapping("/editProfile/{id}")
    public String editProfile (Model model, @PathVariable Long id){
        Optional<Member> optMember = memServ.findById(id);
        if (optMember.isPresent()){
            model.addAttribute("monitor", optMember.get());
            return "USRMEM_02EditProfile";
        } else {
            return "USRADM_02Profile";
        }
    }

    @PostMapping("/editProfile/{id}")
    public String addEditedProle(Model model, @PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String usrname,
                                 @RequestParam String password,
                                 @RequestParam String email,
                                 @RequestParam String NIF,
                                 @RequestParam DateType birthday,
                                 @RequestParam String genre,
                                 @RequestParam String height,
                                 @RequestParam String weight,
                                 @RequestParam String address,
                                 @RequestParam String postalCode,
                                 @RequestParam String phone,
                                 @RequestParam String creditCard,
                                 @RequestParam String additionalInfo,
                                 @RequestParam("image") MultipartFile image) throws IOException {
        Optional<Member> mem = memServ.findById(id);
        String htmlFile;
        if (mem.isPresent()){
            Member member = mem.get();
            member.setName(name);
            member.setSurname(surname);
            member.setUsrname(usrname);
            member.setPassword(password);
            member.setEmail(email);
            member.setNIF(NIF);
            member.setBirthday(birthday);
            member.setGenre(genre);
            member.setHeight(height);
            member.setWeight(weight);
            member.setAddress(address);
            member.setPostalCode(postalCode);
            member.setPhone(phone);
            member.setCreditCard(creditCard);
            member.setAdditionalInfo(additionalInfo);
            member.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
            memServ.save(member);
            htmlFile = "redirect:/member";
        } else {
            //Gestionar error envío de formulario
            htmlFile = "error-404";
        }
        return htmlFile;
    }

    @GetMapping("/monitor/{id}/image")
    public ResponseEntity<Object> downloadMemberImage(@PathVariable long id) throws SQLException{
        Optional<Member> optMon = memServ.findById(id);

        if (optMon.isPresent()){
            Member member = optMon.get();
            if (member.getImageFile() != null){
                Resource file = new InputStreamResource(member.getImageFile().getBinaryStream());

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(member.getImageFile().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
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
    public ResponseEntity<Object> downloadActivityImage(@PathVariable long id) throws SQLException {
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
