package webapp3.webapp3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
import webapp3.webapp3.service.ActivityService;
import webapp3.webapp3.service.ExerciseService;
import webapp3.webapp3.service.ExerciseTableService;
import webapp3.webapp3.service.UserService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Controller
public class MemberController {

    @Autowired
    private ExerciseService exerServ;

    @Autowired
    private ExerciseTableService exerTabServ;

    @Autowired
    private UserService memServ;

    @Autowired
    private ActivityService actServ;


    @GetMapping("/MEMexercise")
    public String exercise (Model model){
        List<ExerciseTable> all = exerTabServ.findAll();
        model.addAttribute("MEMexercises", all);
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
    public ResponseEntity<?> pdfGenerator(@PathVariable Long id){
        try {
            ByteArrayOutputStream baos = exerTabServ.generatePDF(9L, id);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header("Content-disposition", "attachment;filename=\"TablaDeEjercicios.pdf\"")
                    .body(baos.toByteArray());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/MEMeditProfile")
    public String editProfile(Model model) {
        return "USRMEM_02EditProfile";
    }

    @GetMapping("/MEMeditProfile/{id}")
    public String editProfile (Model model, @PathVariable Long id){
        Optional<User> optMember = memServ.findById(id);
        if (optMember.isPresent()){
            model.addAttribute("monitor", optMember.get());
            return "USRMEM_02EditProfile";
        } else {
            return "USRADM_02Profile";
        }
    }

    @PostMapping("/MEMeditProfile/{id}")
    public String addEditedProle(Model model, @PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String usrname,
                                 @RequestParam String password,
                                 @RequestParam String email,
                                 @RequestParam String NIF,
                                 @RequestParam DateType birthday,
                                 @RequestParam String genre,
                                 @RequestParam int height,
                                 @RequestParam Integer weight,
                                 @RequestParam String address,
                                 @RequestParam String postalCode,
                                 @RequestParam String phone,
                                 @RequestParam String creditCard,
                                 @RequestParam String additionalInfo,
                                 @RequestParam("image") MultipartFile image) throws IOException {
        Optional<User> mem = memServ.findById(id);
        String htmlFile;
        if (mem.isPresent()){
            User member = mem.get();
            member.setName(name);
            member.setSurname(surname);
            //member.setUsrname(usrname);
            member.setPassword(password);
            member.setEmail(email);
            member.setNIF(NIF);
            member.setBirthday(birthday);
            //member.setGenre(genre);
            member.setHeight(height);
            //member.appendWeight(weight);
            member.setAddress(address);
            member.setPostalCode(postalCode);
            member.setPhone(phone);
            //member.setCreditCard(creditCard);
            //member.setAdditionalInfo(additionalInfo);
            //member.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
            memServ.save(member);
            htmlFile = "redirect:/member";
        } else {
            //Gestionar error envío de formulario
            htmlFile = "error-404";
        }
        return htmlFile;
    }

    @GetMapping("/MEMmonitor/{id}/image")
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
    public String profile(Model model, @PathVariable long id) {
        Optional<User> mem = memServ.findById(id);
        if(mem.isPresent()){
            model.addAttribute("member", mem.get());
            return "USRMEM_02Profile";
        }
        return "404";
    }

    @GetMapping("/MEMstatistics")
    public String statistics(Model model) {
        int [] clients = new int [12];
        String [][] months = new String [12][4];
        String [] years = {"2019", "2020", "2021", "2022"};
        for (int j = 0; j < years.length; j++) {
            for (int i = 0; i < 12; i++) {
                months[i][j] = "m" + i + j;
                clients[i] = memServ.findByUserTypeAndEntryDate("member", i + 1, years[j]);
                model.addAttribute(months[i][j], clients[i]);
            }
        }
        return "USRMEM_03Estatistics";
    }

    @GetMapping("/MEMactivities")
    public String activities(Model model) {
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

}
