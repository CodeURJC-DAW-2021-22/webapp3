package webapp3.webapp3.controller;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.DateType;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.model.User;
import webapp3.webapp3.service.ActivityService;
import webapp3.webapp3.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ActivityService actServ;

    @Autowired
    private UserService userServ;

    //Statistics of Admin

    @GetMapping("/statistics")
    public String statistics(Model model){
        int [] clients = new int [12];
        String [][] months = new String [12][4];
        String [] years = {"2019", "2020", "2021", "2022"};
        for (int j = 0; j < years.length; j++) {
            for (int i = 0; i < 12; i++) {
                months[i][j] = "m" + i + j;
                clients[i] = userServ.findByUserTypeAndEntryDate("member", i + 1, years[j]);
                model.addAttribute(months[i][j], clients[i]);
            }
        }
        return "USRADM_01Statistics";
    }

    //Activities' management

    @GetMapping("/activities")
    public String activities(Model model){
        List<Activity> all = actServ.findAll();
        model.addAttribute("activitiesList", all);
        return "USRADM_02ActivitiesList";
    }

    @GetMapping("/addNewActivity")
    public String newActivity(Model model){
        return "USRADM_04AddActivity";
    }

    @PostMapping("/addNewActivity")
    public String addNewActivity(Model model, @RequestParam String name, @RequestParam String room,
                                 @RequestParam int price, @RequestParam String description,
                                 @RequestParam int capacity, @RequestParam String monday,
                                 @RequestParam String tuesday, @RequestParam String wednesday,
                                 @RequestParam String thursday, @RequestParam String friday, @RequestParam("image") MultipartFile image) throws IOException {
        Activity activity = new Activity(name, price, description, room, capacity, monday, tuesday, wednesday, thursday, friday);
        if (image.isEmpty()) {
            Resource imageNotAdded = new ClassPathResource("/sample_images/imageNotAddedActivity.jpeg");
            activity.setImage(BlobProxy.generateProxy(imageNotAdded.getInputStream(), imageNotAdded.contentLength()));
        } else {
            activity.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
        }
        actServ.save(activity);
        model.addAttribute("activitiesList", actServ.findAll());
        return "redirect:/activities";
    }

    @GetMapping("/editActivity/{id}")
    public String editActivity(Model model, @PathVariable Long id){
        Optional<Activity> act = actServ.findById(id);
        String htmlFile;
        if (act.isPresent()){
            model.addAttribute("activity", act.get());
            htmlFile = "USRADM_07EditActivity";
        } else {
            htmlFile = "error-404";
        }
        return htmlFile;
    }

    @PostMapping("/editActivity/{id}")
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
                activity.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
            actServ.save(activity);
            htmlFile = "redirect:/activities";
        } else {
            //Gestionar error en el envío del formulario
            htmlFile = "error-404";
        }
        return htmlFile;
    }

    @GetMapping("/activity/{id}")
    public String seeActivityInfo(Model model, @PathVariable long id){
        Optional<Activity> act = actServ.findById(id);

        if (act.isPresent()){
            model.addAttribute("activity", act.get());
            return "USRADM_06SeeActivityInfo";
        } else
            return "USRADM_02ActivitiesList";
    }

    @GetMapping("/activity/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
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

    @GetMapping("/activity/delete/{id}")
    public String deleteActivity(Model model, @PathVariable long id){
        Optional<Activity> optAct = actServ.findById(id);

        if (optAct.isPresent()) {
            Optional<User> monitor = userServ.findByName(optAct.get().getMonitorName());
            User mon = monitor.orElseThrow();
            mon.setACT1(null);
            actServ.delete(id);
        }
        return "redirect:/activities";
    }

    //Monitors' management
    @GetMapping("/monitors")
    public String monitors(Model model){
        model.addAttribute("monitors",userServ.findByUserType("monitor"));
        return "USRADM_03MonitorsList";
    }

    @GetMapping("/addNewMonitor")
    public String newMonitor(Model model){
        model.addAttribute("freeAct", actServ.findByMonitorName(null));
        return "USRADM_08AddMonitor";
    }

    @PostMapping("/addNewMonitor")
    public String addNewMonitor(Model model, @RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam String NIF,
                                @RequestParam String email,
                                @RequestParam String address,
                                @RequestParam String postalCode,
                                @RequestParam String phone,
                                @RequestParam String birthdayDate,
                                @RequestParam String hiringDate,
                                @RequestParam Long activityName,
                                @RequestParam String description,
                                @RequestParam(name = "image", required = false) MultipartFile image) throws IOException {
        DateType birthday = new DateType(birthdayDate.substring(0, 4), birthdayDate.substring(5, 7), birthdayDate.substring(8, 10));
        DateType hiring = new DateType(hiringDate.substring(0, 4), hiringDate.substring(5, 7), hiringDate.substring(8, 10));
        User monitor = new User(name, surname, NIF, email, address, postalCode, phone, birthday,
                 hiring, description, passwordEncoder.encode("monitor"));

        if (activityName != -1) {
            Optional<Activity> activity = actServ.findById(activityName);
            if (activity.isPresent()) {
                activity.get().setMonitorName(name);
                actServ.save(activity.get());
                monitor.setACT1(activity.get());
            }//Error activity not found
        }

        if (image.isEmpty()) {
            Resource imageNotAdded = new ClassPathResource("/sample_images/imageNotAddedPeople.jpeg");
            monitor.setImage(BlobProxy.generateProxy(imageNotAdded.getInputStream(), imageNotAdded.contentLength()));
        } else {
            monitor.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
        }
        userServ.save(monitor);

        return "redirect:/monitors";
    }

    @GetMapping("/monitor/{id}")
    public String seeMonitorInfo(Model model, @PathVariable Long id){
        Optional<User> optMonitor = userServ.findById(id);

        String html = "";
        if (optMonitor.isPresent()){
            User monitor = optMonitor.get();
            model.addAttribute("monitor", monitor);
            html = "USRADM_10SeeMonitorInfo";
        } else {
            html = "USRADM_03MonitorList";
        }
        return html;
    }

    @GetMapping("/monitor/{id}/image")
    public ResponseEntity<Object> downloadMonitorImage(@PathVariable long id) throws SQLException{
        Optional<User> optMon = userServ.findById(id);

        if (optMon.isPresent()){
            User monitor = optMon.get();
            if (monitor.getImage() != null){
                Resource file = new InputStreamResource(monitor.getImage().getBinaryStream());

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(monitor.getImage().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/editMonitor/{id}")
    public String editMonitor(Model model, @PathVariable Long id){
        Optional<User> optMonitor = userServ.findById(id);

        String html = "";
        if (optMonitor.isPresent()){
            model.addAttribute("monitor", optMonitor.get());
            model.addAttribute("freeAct", actServ.findByMonitorName(null));
            html = "USRADM_11EditMonitor";
        } else {
            html = "USRADM_03MonitorList";
        }
        return html;
    }

    @PostMapping("/editMonitor/{id}")
    public String addEditedMonitor(Model model, @PathVariable Long id,
                                   @RequestParam String name,
                                   @RequestParam String surname,
                                   @RequestParam String NIF,
                                   @RequestParam String email,
                                   @RequestParam String address,
                                   @RequestParam String postalCode,
                                   @RequestParam String phone,
                                   @RequestParam String birthdayDate,
                                   @RequestParam String hiringDate,
                                   @RequestParam(required = false) Long activityName,
                                   @RequestParam String description,
                                   @RequestParam(name = "image", required = false) MultipartFile image) throws IOException {
        Optional<User> mon = userServ.findById(id);
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
            user.getBirthday().setDay(birthdayDate.substring(8, 10));
            user.getBirthday().setMonth(birthdayDate.substring(5, 7));
            user.getBirthday().setYear(birthdayDate.substring(0,4));
            user.getBirthday().generateSpanishFormat();
            user.getHiringDate().setDay(hiringDate.substring(8, 10));
            user.getHiringDate().setMonth(hiringDate.substring(5, 7));
            user.getHiringDate().setYear(hiringDate.substring(0, 4));
            user.getHiringDate().generateSpanishFormat();

            if (activityName == -1) {
                //Delete association to an activity
                Activity act = user.getACT1();
                act.setMonitorName(null);
                actServ.save(act);
                user.setACT1(null);
            } else if (activityName != -1 && user.getACT1() == null) {
                //Add activity to a monitor without previous activity
                Optional<Activity> activityOptional = actServ.findById(activityName);
                activityOptional.orElseThrow().setMonitorName(user.getName());
                actServ.save(activityOptional.get());
                user.setACT1(activityOptional.get());
            } else if (activityName != -1 && user.getACT1() != null && !user.getACT1().getId().equals(activityName)) {
                //Add activity to monitor with previous activity associated
                //1 -> Change monitorName in old activity
                if (user.getACT1() != null) {
                    user.getACT1().setMonitorName(null);
                    actServ.save(user.getACT1());
                }
                //2 -> Change monitor name in new Activity
                Optional<Activity> activityOptional = actServ.findById(activityName);
                activityOptional.orElseThrow().setMonitorName(user.getName());
                actServ.save(activityOptional.get());
                //3 -> Add activity to user
                user.setACT1(activityOptional.get());
            }

            user.setDescription(description);
            if (!image.isEmpty())
                user.setImage(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
            userServ.save(user);
            htmlFile = "redirect:/monitors";
        } else {
            //Error monitor not found
            htmlFile = "error-404";
        }
        return htmlFile;
    }

    @GetMapping("/delete/monitor/{id}")
    public String deleteMonitor(Model model, @PathVariable long id){
        userServ.delete(id);
        return "redirect:/monitors";
    }

    //Clients' management
    @GetMapping("/clients")
    public String clients(Model model){
        List<User> all = userServ.findByUserType("member");
        Page<User> exerTabPage = userServ.findPageClient(0);
        model.addAttribute("clientList", all);
        model.addAttribute("list", exerTabPage.toList());
        model.addAttribute("last", exerTabPage.getTotalPages());
        return "USRADM_12Clients";
    }

    @GetMapping("/clients/page/{page}")
    public String getClientPage(Model model, @PathVariable int page){
        Page<User> client = userServ.findPageClient(page);
        model.addAttribute("list", client.toList());

        return "USRADM_12CLientsAJAX";

    }

    @PostMapping("/deleteClients")
    public String deleteClients(Model model, @RequestParam(required = false) List<Long> id){
        if(id != null) {
            for (Long l : id) {
                userServ.delete(l);
            }
        }
        List<User> all = userServ.findAll();
        model.addAttribute("clientList", all);
        return "redirect:/clients";
    }

    @GetMapping("/adminimage")
    public ResponseEntity<Object> downloadAdministratorImage(HttpServletRequest request) throws SQLException{
        Optional<User> optAdm = userServ.findByEmail(request.getUserPrincipal().getName());

        if (optAdm.isPresent()){
            User monitor = optAdm.get();
            if (monitor.getImage() != null){
                Resource file = new InputStreamResource(monitor.getImage().getBinaryStream());

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(monitor.getImage().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
