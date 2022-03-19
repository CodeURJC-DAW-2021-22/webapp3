package webapp3.webapp3.controller;


import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
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
import webapp3.webapp3.model.*;
import webapp3.webapp3.service.ActivityService;
import webapp3.webapp3.service.ExerciseService;
import webapp3.webapp3.service.ExerciseTableService;
import webapp3.webapp3.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
        model.addAttribute("monitor", user.get());
        Activity act = user.get().getACT1();
        if (act != null) {
            model.addAttribute("getACT1", true);
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
        }else
            model.addAttribute("getACT1", false);
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

    @GetMapping("/MONprofile")
    public String profile(Model model, HttpServletRequest request) {
        String emailName = request.getUserPrincipal().getName();
        Optional<User> mon = monServ.findByEmail(emailName);
        model.addAttribute("monitor", mon.get());
        return "USRMON_02Pofile";
    }

    //edit profile page
    @GetMapping("/MONeditProfile")
    public String editProfile(Model model, HttpServletRequest request) {
        String emailName = request.getUserPrincipal().getName();
        Optional<User> mon = monServ.findByEmail(emailName);
        User user = mon.orElseThrow();
        model.addAttribute("monitor", user);
        return "USRMON_05EditProfile";
    }

    @PostMapping("/MONeditProfile")
    public String addEditedMonitor(Model model, HttpServletRequest request,
                                   @RequestParam String name,
                                   @RequestParam String surname,
                                   @RequestParam String NIF,
                                   @RequestParam String email,
                                   @RequestParam String address,
                                   @RequestParam String postalCode,
                                   @RequestParam String phone,
                                   @RequestParam String birthdayDate,
                                   @RequestParam String hiringDate,
                                   @RequestParam String description,
                                   @RequestParam(name = "image", required = false) MultipartFile image) throws IOException {
        String emailName = request.getUserPrincipal().getName();
        Optional<User> mon = monServ.findByEmail(emailName);
        model.addAttribute("monitor", mon.get());
        String htmlFile;
        if (mon.isPresent()){
            User user = mon.get();
            user.setName(name);
            user.setSurname(surname);
            user.setNIF(NIF);
            user.setEmail(email);
            user.setAddress(address);
            user.setPostalCode(postalCode);
            user.setPhone(phone);
            DateType date = new DateType(birthdayDate.substring(0,4), birthdayDate.substring(5, 7), birthdayDate.substring(8, 10));
            user.setBirthday(date);
            date = new DateType(hiringDate.substring(0,4), hiringDate.substring(5, 7), hiringDate.substring(8, 10));
            user.setHiringDate(date);
            user.setDescription(description);
            if (!image.isEmpty())
                user.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
            monServ.save(user);
            htmlFile = "redirect:/MONprofile";
        } else {
            //Error monitor not found
            htmlFile = "error-404";
        }
        return htmlFile;
    }

    //exercise table page
    @GetMapping("/MONexerciseTable")
    public String exerciseTable(Model model, HttpServletRequest request) {
        List<ExerciseTable> all = exerciseTableServ.findAll();
        Page<ExerciseTable> exerTabPage = exerciseTableServ.findPage(0);
        model.addAttribute("exerciseTableList", all);
        model.addAttribute("monitor", monServ.findByEmail(request.getUserPrincipal().getName()).orElseThrow());
        model.addAttribute("listMON", exerTabPage.toList());
        model.addAttribute("last", exerTabPage.getTotalPages());
        return "USRMON_03ExerciseTable";
    }

    @GetMapping("/MONexerciseTable/{id}")
    public String seeExerciseTableInfo(Model model, @PathVariable long id, HttpServletRequest request) {
        Optional<ExerciseTable> exerciseTable = exerciseTableServ.findById(id);
        model.addAttribute("monitor", monServ.findByEmail(request.getUserPrincipal().getName()).orElseThrow());
        if (exerciseTable.isPresent()) {
            model.addAttribute("exerciseTable", exerciseTable.get());
            return "USRMON_09_SeeExerciseTableInfo";
        } else
            return "USRMON_03ExerciseTable";
    }

    //ajax
    @GetMapping("/MONexerciseTable/page/{page}")
    public String getExerciseTablePageMonitor(Model model, @PathVariable int page){
        Page<ExerciseTable> exerTabPage = exerciseTableServ.findPage(page);
        model.addAttribute("listMON", exerTabPage.toList());

        return "USRMON_03ExerciseTableAJAX";
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
    public String newExerciseTable(Model model, HttpServletRequest request) {
        List<User> all = monServ.findByUserType("monitor");
        Page<User> userPage = monServ.findPageClient(0, "monitor");

        List<Exercise> allEx = exerServ.findAll();
        Page<Exercise> exerPage = exerServ.findPage(0);

        model.addAttribute("monitor", monServ.findByEmail(request.getUserPrincipal().getName()).orElseThrow());
        model.addAttribute("exercises", exerServ.findAll());

        model.addAttribute("exerList", allEx);
        model.addAttribute("clientList", all);
        model.addAttribute("list", exerPage.toList());
        model.addAttribute("last", exerPage.getTotalPages());

        return "USRMON_06AddExerciseTable";
    }

    //ajax for adding new exercises
    @GetMapping("/MONaddNewExercise/page/{page}")
    public String getExercisesPageMonitor(Model model, @PathVariable int page){
        Page<Exercise> exerTabPage = exerServ.findPage(page);
        model.addAttribute("list", exerTabPage.toList());

        return "USRMON_06AddExerciseTableAJAX";

    }

    @PostMapping("/MONaddNewExerciseTable")
    public String addNewExerciseTable(Model model,
                                      @RequestParam(required = false) List<Long> id,
                                      @RequestParam String name,
                                      @RequestParam String description,
                                      @RequestParam("image") MultipartFile image) throws IOException {
        ExerciseTable exerciseTable = new ExerciseTable(name, description);
        if (!id.isEmpty()){
            List<Exercise> auxList = new ArrayList<>(id.size());

            for (Long l: id){
                auxList.add(exerServ.findById(l).orElseThrow());
            }
            exerciseTable.setExercises(auxList);
        }
        if (image.isEmpty()) {
            Resource imageNotAdded = new ClassPathResource("/sample_images/imageNotAddedActivity.jpeg");
            exerciseTable.setImage(BlobProxy.generateProxy(imageNotAdded.getInputStream(), imageNotAdded.contentLength()));
        } else {
            exerciseTable.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
        }
        exerciseTableServ.save(exerciseTable);
        model.addAttribute("exerciseTableList", exerciseTableServ.findAll());
        return "redirect:/MONexerciseTable";
    }

    //edit exercise table
    @GetMapping("/MONeditExerciseTable/{id}")
    public String editExerciseTable(Model model, @PathVariable Long id, HttpServletRequest request) {
        Optional<ExerciseTable> exerciseTable = exerciseTableServ.findById(id);
        model.addAttribute("monitor", monServ.findByEmail(request.getUserPrincipal().getName()).orElseThrow());
        String htmlFile;
        if (exerciseTable.isPresent()) {
            model.addAttribute("exerciseTable", exerciseTable.get());
            htmlFile = "USRMON_10EditExerciseTable";
        } else {
            htmlFile = "error-404";
        }
        return htmlFile;
    }

    @PostMapping("/MONeditExerciseTable/{var}")
    public String addEditedActivity(Model model, @RequestParam String name,
                                    @RequestParam String description,
                                    @PathVariable Long var,
                                    @RequestParam("image") MultipartFile image,
                                    @RequestParam(required = false) List<Long> id) throws IOException {
        Optional<ExerciseTable> exTab = exerciseTableServ.findById(var);
        String htmlFile;
        if (exTab.isPresent()) {
            ExerciseTable ex = exTab.get();
            ex.setName(name);
            ex.setDescription(description);
            List<Exercise> exList = new ArrayList<>(id.size());
            for (Long l : id) {
                exList.add(exerServ.findById(l).orElseThrow());
            }
            ex.setExercises(exList);
            if (!image.isEmpty())
                ex.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
            exerciseTableServ.save(ex);
            htmlFile = "redirect:/MONexerciseTable";
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
    public String activities(Model model, HttpServletRequest request) {
        List<Activity> all = actServ.findAll();
        model.addAttribute("activitiesList", all);
        model.addAttribute("monitor", monServ.findByEmail(request.getUserPrincipal().getName()).orElseThrow());
        return "USRMON_04GrupalActivities";
    }

    @GetMapping("/MONactivity/{id}")
    public String seeActivityInfo(Model model, @PathVariable long id, HttpServletRequest request) {
        model.addAttribute("monitor", monServ.findByEmail(request.getUserPrincipal().getName()).orElseThrow());
        Optional<Activity> activity = actServ.findById(id);
        if (activity.isPresent()) {
            model.addAttribute("mon", true);
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
