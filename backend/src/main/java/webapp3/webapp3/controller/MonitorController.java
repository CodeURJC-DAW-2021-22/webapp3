package webapp3.webapp3.controller;


import org.hibernate.engine.jdbc.BlobProxy;
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

import javax.servlet.http.HttpServletRequest;
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
    @GetMapping("/MONschedule")
    public String schedule(Model model, HttpServletRequest request) {
        String name = request.getUserPrincipal().getName();
        Optional<User> user = monServ.findByEmail(name);
        Activity act = user.get().getACT1();

        model.addAttribute("name", act.getName());
        model.addAttribute("description", act.getDescription());
        if (!act.getMonday().equals("")) {
            model.addAttribute("monAct", true);
            model.addAttribute("monday", act.getMonday());
        }
        if (!act.getTuesday().equals("")) {
            model.addAttribute("tusAct", true);
            model.addAttribute("tuesday", act.getTuesday());
        }
        if (!act.getWednesday().equals("")) {
            model.addAttribute("wenAct", true);
            model.addAttribute("wednesday", act.getWednesday());
        }
        if (!act.getThursday().equals("")) {
            model.addAttribute("thuAct", true);
            model.addAttribute("thursday", act.getThursday());
        }
        if (!act.getFriday().equals("")) {
            model.addAttribute("friAct", true);
            model.addAttribute("friday", act.getFriday());
        }
        return "USRMON_01Schedule";
    }

    @GetMapping("/MONschedule/image")
    public ResponseEntity<Object> scheduleImage(HttpServletRequest request) throws SQLException {
        String name = request.getUserPrincipal().getName();
        Optional<User> user = monServ.findByEmail(name);

        Activity actividad = user.get().getACT1();

        if (actividad.getImage() != null) {

            Resource file = new InputStreamResource(actividad.getImage().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(actividad.getImage().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //profile page
    @GetMapping("/MON/{id}/image")
    public ResponseEntity<Object> downloadMemberImage(@PathVariable long id) throws SQLException {
        Optional<User> optMon = monServ.findById(id);

        if (optMon.isPresent()) {
            User monitor = optMon.get();
            if (monitor.getImage() != null) {
                Resource file = new InputStreamResource(monitor.getImage().getBinaryStream());

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(monitor.getImage().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/MONprofile/{id}")
    public String profile(Model model, @PathVariable long id, HttpServletRequest request) {
        String emailName = request.getUserPrincipal().getName();
        Optional<User> mon = monServ.findByEmail(emailName);
        User user = mon.orElseThrow();
        model.addAttribute("id", user.getId());
        model.addAttribute("monitor", user);
        return "USRMON_02Profile";
    }

    //edit profile page
    @GetMapping("/MONeditProfile/{id}")
    public String editProfile(Model model, @PathVariable Long id, HttpServletRequest request) {
        String emailName = request.getUserPrincipal().getName();
        Optional<User> mon = monServ.findByEmail(emailName);
        User user = mon.orElseThrow();
        model.addAttribute("id", user.getId());
        model.addAttribute("monitor", user);
        return "USRMON_05EditProfile";
    }

    @PostMapping("/MONeditProfile/{id}")
    public String addEditedProfile(Model model, @PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam String surname,
                                 @RequestParam String email,
                                 @RequestParam String NIF,
                                 @RequestParam String birthday,
                                 @RequestParam String hiring,
                                 @RequestParam String address,
                                 @RequestParam String postalCode,
                                 @RequestParam String phone,
                                 @RequestParam String description,
                                 @RequestParam("image") MultipartFile image) throws IOException {
        Optional<User> mon = monServ.findById(id);
        User monitor = mon.orElseThrow();
        monitor.setName(name);
        monitor.setSurname(surname);
        monitor.setEmail(email);
        monitor.setNIF(NIF);
        monitor.getBirthday().setDay(birthday.substring(8, 10));
        monitor.getBirthday().setMonth(birthday.substring(5, 7));
        monitor.getBirthday().setYear(birthday.substring(0, 4));
        monitor.getHiringDate().setDay(hiring.substring(8, 10));
        monitor.getHiringDate().setMonth(hiring.substring(5, 7));
        monitor.getHiringDate().setYear(hiring.substring(0, 4));
        monitor.setAddress(address);
        monitor.setPostalCode(postalCode);
        monitor.setPhone(phone);
        monitor.setDescription(description);
        if (!image.isEmpty()) {
            monitor.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
        }
        monServ.save(monitor);
        return "redirect:/MONprofile/{id}";
    }

    //exercise table page
    @GetMapping("/MONexerciseTable")
    public String exerciseTable(Model model) {
        List<ExerciseTable> all = exerciseTableServ.findAll();
        model.addAttribute("exerciseTableList", all);
        return "USRMON_03ExerciseTable";
    }

    @GetMapping("/MONexerciseTable/{id}")
    public String seeExerciseTableInfo(Model model, @PathVariable long id) {
        Optional<ExerciseTable> exerciseTable = exerciseTableServ.findById(id);

        if (exerciseTable.isPresent()) {
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
    public String newExerciseTable(Model model) {
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
    public String editExerciseTable(Model model, @PathVariable Long id) {
        Optional<ExerciseTable> exerciseTable = exerciseTableServ.findById(id);
        String htmlFile;
        if (exerciseTable.isPresent()) {
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
                                    @RequestParam("image") MultipartFile image) throws IOException {
        Optional<Activity> act = actServ.findById(id);
        String htmlFile;
        if (act.isPresent()) {
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
    public String deleteExerciseTable(Model model, @PathVariable long id) {
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
