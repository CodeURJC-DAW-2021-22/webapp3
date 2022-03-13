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
public class MonitorController {


    @Autowired
    private ExerciseService exerServ;

    @Autowired
    private ExerciseTableService exerciseTableServ;

    @Autowired
    private ActivityService actServ;

    @Autowired
    private UserService monServ;

    //schedule page
    @GetMapping("/MONschedule/{id}")
    public String schedule(Model model, @PathVariable long id) {
        Optional<Activity> act = actServ.findById(id);
        String result;
        if (act.isPresent()) {
            result = "USRMON_01Schedule";
            model.addAttribute("name", act.get().getName());
            model.addAttribute("description", act.get().getDescription());
            if (!act.get().getMonday().equals("")) {
                model.addAttribute("monAct", true);
                model.addAttribute("monday", act.get().getMonday());
            }
            if (!act.get().getTuesday().equals("")) {
                model.addAttribute("tusAct", true);
                model.addAttribute("tuesday", act.get().getTuesday());
            }
            if (!act.get().getWednesday().equals("")) {
                model.addAttribute("wenAct", true);
                model.addAttribute("wednesday", act.get().getWednesday());
            }
            if (!act.get().getThursday().equals("")) {
                model.addAttribute("thuAct", true);
                model.addAttribute("thursday", act.get().getThursday());
            }
            if (!act.get().getFriday().equals("")) {
                model.addAttribute("friAct", true);
                model.addAttribute("friday", act.get().getFriday());
            }
        } else {
            result = "404";
        }
        return result;
    }

    @GetMapping("/MONschedule/{id}/image")
    public ResponseEntity<Object> scheduleImage(@PathVariable long id) throws SQLException {
        Optional<Activity> actividad = actServ.findById(id);

        if (actividad.isPresent() && actividad.get().getImage() != null) {

            Resource file = new InputStreamResource(actividad.get().getImage().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(actividad.get().getImage().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //profile page
    @GetMapping("/MON/{id}/image")
    public ResponseEntity<Object> downloadMemberImage(@PathVariable long id) throws SQLException {
        Optional<User> optMon = monServ.findById(id);

        if (optMon.isPresent()) {
            User member = optMon.get();
            if (member.getImage() != null) {
                Resource file = new InputStreamResource(member.getImage().getBinaryStream());

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(member.getImage().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/MONprofile/{id}")
    public String profile(Model model, @PathVariable long id) {
        model.addAttribute("id", "9");
        Optional<User> mon = monServ.findById(id);
        if (mon.isPresent()) {
            model.addAttribute("member", mon.get());
            return "USRMON_02Pofile";
        }
        return "404";
    }

    //edit profile page
    @GetMapping("/MONeditProfile")
    public String editProfile(Model model) {
        model.addAttribute("id", "9");
        return "USRMON_05EditProfile";
    }

    @GetMapping("/MONeditProfile/{id}")
    public String editProfile(Model model, @PathVariable Long id) {
        model.addAttribute("id", "9");
        Optional<User> optMonitor = monServ.findById(id);
        if (optMonitor.isPresent()) {
            model.addAttribute("monitor", optMonitor.get());
            return "USRMON_05EditProfile";
        } else {
            return "USRMON_02Profile";
        }
    }

    @PostMapping("/MONeditProfile/{id}")
    public String addEditedProle(Model model, @PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String usrname,
                                 @RequestParam String password,
                                 @RequestParam String email,
                                 @RequestParam String NIF,
                                 @RequestParam DateType birthday,
                                 @RequestParam String gender,
                                 @RequestParam int height,
                                 @RequestParam Integer weight,
                                 @RequestParam String address,
                                 @RequestParam String postalCode,
                                 @RequestParam String phone,
                                 @RequestParam String creditCard,
                                 @RequestParam String additionalInfo,
                                 @RequestParam("image") MultipartFile image) throws IOException {
        Optional<User> mon = monServ.findById(id);
        String htmlFile;
        if (mon.isPresent()) {
            User monitor = mon.get();
            monitor.setName(name);
            monitor.setSurname(surname);
            //member.setUsrname(usrname);
            monitor.setEncodedPassword(password);
            monitor.setEmail(email);
            monitor.setNIF(NIF);
            monitor.setBirthday(birthday);
            //member.setGenre(genre);
            monitor.setHeight(height);
            //member.appendWeight(weight);
            monitor.setAddress(address);
            monitor.setPostalCode(postalCode);
            monitor.setPhone(phone);
            //member.setCreditCard(creditCard);
            //member.setAdditionalInfo(additionalInfo);
            //member.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
            monServ.save(monitor);
            htmlFile = "redirect:/member";
        } else {
            //Gestionar error envío de formulario
            htmlFile = "error-404";
        }
        return htmlFile;
    }

    //exercise table page
    @GetMapping("/MONexerciseTable")
    public String exerciseTable(Model model) {
        List<ExerciseTable> all = exerciseTableServ.findAll();
        model.addAttribute("exerciseTableList", all);
        return "USRMON_03ExerciseTable";
    }

    @GetMapping("/MONexerciseTable/{id}")
    public String seeExerciseTableInfo(Model model, @PathVariable long id){
        Optional<ExerciseTable> exerciseTable = exerciseTableServ.findById(id);

        if (exerciseTable.isPresent()){
            model.addAttribute("exerciseTable", exerciseTable.get());
            return "USRMON_09SeeExerciseTableInfo";
        } else
            return "USRMON_03ExercsieTable";
    }

    @GetMapping("/MONexerciseTable/{id}/image")
    public ResponseEntity<Object> downloadExerciseImage(@PathVariable long id) throws SQLException {
        Optional<ExerciseTable> optExerTab = exerciseTableServ.findById(id);

        if (optExerTab.isPresent()) {
            ExerciseTable exerciseTab = optExerTab.get();
            if (exerciseTab.getImage() != null) {
                Resource file = new InputStreamResource(exerciseTab.getImage().getBinaryStream());
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(exerciseTab.getImage().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }

    //add exercise table page
    @GetMapping("/MONaddNewExerciseTable")
    public String newExerciseTable(Model model){
        return "USRMON_06AddExerciseTable";
    }

    // FALTA
    @PostMapping("/MONaddNewExerciseTable")
    public String addNewExerciseTable(Model model, @RequestParam String name, @RequestParam String room,
                                 @RequestParam int price, @RequestParam String description,
                                 @RequestParam int capacity, @RequestParam String monday,
                                 @RequestParam String tuesday, @RequestParam String wednesday,
                                 @RequestParam String thursday, @RequestParam String friday, @RequestParam("image") MultipartFile image) throws IOException {
        Activity activity = new Activity(name, price, description, room, capacity, monday, tuesday, wednesday, thursday, friday);
        if (image.isEmpty()) {
            //Resource imageNotAdded = new ClassPathResource("/sample_images/imageNotAddedActivity.jpeg");
            //activity.setImage(BlobProxy.generateProxy(imageNotAdded.getInputStream(), imageNotAdded.contentLength()));
        } else {
            //activity.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
        }
        actServ.save(activity);
        model.addAttribute("activitiesList", actServ.findAll());
        return "redirect:/activities";
    }

    //edit exercise table
    @GetMapping("/MONeditExerciseTable/{id}")
    public String editExerciseTable(Model model, @PathVariable Long id){
        Optional<ExerciseTable> exerciseTable = exerciseTableServ.findById(id);
        String htmlFile;
        if (exerciseTable.isPresent()){
            model.addAttribute("exerciseTable", exerciseTable.get());
            htmlFile = "USRMON_10EditExerciseTable";
        } else {
            htmlFile = "error-404";
        }
        return htmlFile;
    }

    // FALTA
    @PostMapping("/MONeditActivity/{id}")
    public String addEditedActivity(Model model, @RequestParam String name, @RequestParam String room,
                                    @RequestParam int price, @RequestParam String description,
                                    @RequestParam int capacity, @RequestParam String monday,
                                    @RequestParam String tuesday, @RequestParam String wednesday,
                                    @RequestParam String thursday, @RequestParam String friday, @PathVariable Long id,
                                    @RequestParam("image") MultipartFile image) throws IOException{
        Optional<Activity> act = actServ.findById(id);
        String htmlFile;
        if (act.isPresent()){
            Activity activity = act.get();
            activity.setName(name);
            activity.setRoom(room);
            activity.setPrice(price);
            activity.setDescription(description);
            activity.setCapacity(capacity);
            activity.setMonday(monday);
            activity.setTuesday(tuesday);
            activity.setWednesday(wednesday);
            activity.setThursday(thursday);
            activity.setFriday(friday);
            if (!image.isEmpty())
                //activity.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
            actServ.save(activity);
            htmlFile = "redirect:/activities";
        } else {
            //Gestionar error en el envío del formulario
            htmlFile = "error-404";
        }
        return htmlFile;
    }

    // delete exercise table page
    @GetMapping("/MONexerciseTable/delete/{id}")
    public String deleteExerciseTable(Model model, @PathVariable long id){
        Optional<ExerciseTable> exerciseTable = exerciseTableServ.findById(id);

        if (exerciseTable.isPresent()) {
            //Optional<User> monitor = userServ.findByName(optAct.get().getMonitorName());
            //User mon = monitor.orElseThrow();
            //mon.setACT1(null);
            exerciseTableServ.delete(id);
        }
        return "redirect:/exerciseTable";
    }


    //grupal activities page
    @GetMapping("/MONactivities")
    public String activities(Model model) {
        List<Activity> all = actServ.findAll();
        model.addAttribute("activitiesList", all);
        return "USRMON_04GrupalActivities";
    }

    @GetMapping("/MONactivity/{id}")
    public String seeActivityInfo(Model model, @PathVariable long id) {
        Optional<Activity> activity = actServ.findById(id);
        if (activity.isPresent()) {
            model.addAttribute("activity", activity.get());
            return "USRMON_08SeeActivityInfo";
        } else
            return "USRMON_04GrupalActivities";
    }

    @GetMapping("/MONactivity/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        Optional<Activity> optionalActivity = actServ.findById(id);
        if (optionalActivity.isPresent()) {
            Activity activity = optionalActivity.get();
            if (activity.getImage() != null) {
                Resource file = new InputStreamResource(activity.getImage().getBinaryStream());
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(activity.getImage().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
