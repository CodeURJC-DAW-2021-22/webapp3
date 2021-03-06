package webapp3.webapp3.controller;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import webapp3.webapp3.model.*;
import webapp3.webapp3.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;



@Controller
public class MemberController {

    @Autowired
    private UserExerciseTableService usExServ;

    @Autowired
    private ExerciseService exerServ;

    @Autowired
    private ExerciseTableService exerTabServ;

    @Autowired
    private UserService memServ;

    @Autowired
    private ActivityService actServ;


    @GetMapping("/MEMexercise")
    public String exercise (Model model, HttpServletRequest request){
        User user = memServ.findByEmail(request.getUserPrincipal().getName()).orElseThrow();
        List<ExerciseTable> all = exerTabServ.findAll();
        Page<ExerciseTable> exerTabPage = exerTabServ.findPage(0);
        model.addAttribute("MEMexercises", all);
        model.addAttribute("id", user.getId());
        model.addAttribute("list", exerTabPage.toList());
        model.addAttribute("last", exerTabPage.getTotalPages());
        return "USRMEM_01ExerciseTable";
    }

    @GetMapping("/MEMexercise/{id}/image")
    public ResponseEntity<Object> downloadExerciseImage(@PathVariable long id) throws SQLException {
        Optional<ExerciseTable> optExerTab = exerTabServ.findById(id);

        if (optExerTab.isPresent()){
            ExerciseTable exerciseTab = optExerTab.get();
            if (exerciseTab.getImage() != null){
                Resource file = new InputStreamResource(exerciseTab.getImage().getBinaryStream());
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(exerciseTab.getImage().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/MEMexerciseTable/{id}/pdf")
    public ResponseEntity<?> pdfGenerator(@PathVariable Long id, HttpServletRequest request){
        return exerTabServ.getPdf(id, request);
    }

    //ajax
    @GetMapping("/MEMexerciseTable/page/{page}")
    public String getExerciseTablePage(Model model, @PathVariable int page){
        Page<ExerciseTable> exerTabPage = exerTabServ.findPage(page);
        model.addAttribute("list", exerTabPage.toList());

        return "USRMEM_01ExerciseTableAJAX";
    }

    @GetMapping("/MEMeditProfile/{id}")
    public String editProfile (Model model, @PathVariable Long id, HttpServletRequest request){
        String emailName = request.getUserPrincipal().getName();
        Optional<User> mem = memServ.findByEmail(emailName);
        User user = mem.orElseThrow();
        model.addAttribute("id", user.getId());
        model.addAttribute("member", user);
        return "USRMEM_02EditProfile";
    }

    @PostMapping("/MEMeditProfile/{id}")
    public String addEditedProfile(Model model, @PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String NIF,
                                 @RequestParam String birthday,
                                 @RequestParam String height,
                                 @RequestParam String weight,
                                 @RequestParam String address,
                                 @RequestParam String postalCode,
                                 @RequestParam String phone,
                                 @RequestParam String additionalInfo,
                                 @RequestParam(name = "image", required = false) MultipartFile image) throws IOException {
        Optional<User> mem = memServ.findById(id);
        User member = mem.orElseThrow();
        member.setName(name);
        member.setSurname(surname);
        member.setNIF(NIF);
        DateType date = new DateType(birthday.substring(0,4), birthday.substring(5, 7), birthday.substring(8, 10));
        member.setBirthday(date);
        member.setHeight(Integer.parseInt(height));
        member.setWeight(Integer.parseInt(weight));
        member.setAddress(address);
        member.setPostalCode(postalCode);
        member.setPhone(phone);
        member.setMedicalInfo(additionalInfo);
        if (!image.isEmpty()) {
            member.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
        }
        memServ.save(member);
        return "redirect:/MEMprofile/{id}";
    }

    @GetMapping("/MEM/{id}/image")
    public ResponseEntity<Object> downloadMemberImage(@PathVariable long id) throws SQLException{
        Optional<User> optMon = memServ.findById(id);

        if (optMon.isPresent()){
            User member = optMon.get();
            if (member.getImage() != null){
                Resource file = new InputStreamResource(member.getImage().getBinaryStream());

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(member.getImage().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/MEMprofile/{id}")
    public String profile(Model model, @PathVariable long id, HttpServletRequest request) {
        String emailName = request.getUserPrincipal().getName();
        Optional<User> mem = memServ.findByEmail(emailName);
        User user = mem.orElseThrow();
        model.addAttribute("id", user.getId());
        model.addAttribute("member", user);
        return "USRMEM_02Profile";
    }

    @GetMapping("/MEMstatistics")
    public String statistics(Model model, HttpServletRequest request) {
        String emailName = request.getUserPrincipal().getName();
        Optional<User> mem = memServ.findByEmail(emailName);
        User user = mem.orElseThrow();
        HashMap<String, Integer> ex = usExServ.findExercisesTables(user.getId());
        model.addAttribute("id", user.getId());
        model.addAttribute("TableList", ex.keySet());
        model.addAttribute("List", ex.values());
        return "USRMEM_03Estatistics";
    }

    @GetMapping("/MEMactivities")
    public String activities(Model model, HttpServletRequest request) {
        String emailName = request.getUserPrincipal().getName();
        Optional<User> mem = memServ.findByEmail(emailName);
        User user = mem.orElseThrow();
        model.addAttribute("id", user.getId());
        List<Activity> all = actServ.findAll();
        model.addAttribute("activities", all);
        return "USRMEM_04Activities";
    }

    @GetMapping("/MEMactivity/{id}/image")
    public ResponseEntity<Object> downloadActivityImage(@PathVariable long id) throws SQLException {
        Optional<Activity> optAct = actServ.findById(id);

        if (optAct.isPresent()){
            Activity activity = optAct.get();
            if (activity.getImage() != null){
                Resource file = new InputStreamResource(activity.getImage().getBinaryStream());

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(activity.getImage().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/MEMexerciseTable/{id}/done")
    public String doneExerciseTable(@PathVariable long id, HttpServletRequest request){
        usExServ.save(new UserExerciseTable(memServ.findByEmail(request.getUserPrincipal().getName()).orElseThrow(), exerTabServ.findById(id).orElseThrow()));
        return "redirect:/MEMexercise";
    }

    @GetMapping("/MEMseeActivityInfo/{id}")
    public String seeActivityInfo(Model model, @PathVariable long id, HttpServletRequest request){
        String emailName = request.getUserPrincipal().getName();
        Optional<User> mem = memServ.findByEmail(emailName);
        User user = mem.orElseThrow();
        model.addAttribute("id", user.getId());
        Optional<Activity> activity = actServ.findById(id);
        model.addAttribute("mon", false);
        model.addAttribute("activity", activity.orElseThrow());
        return "USRMON_08SeeActivityInfo";
    }

}
