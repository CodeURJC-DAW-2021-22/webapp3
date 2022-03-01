package webapp3.webapp3.controller;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.DateType;
import webapp3.webapp3.model.Monitor;
import webapp3.webapp3.service.ActivityService;
import webapp3.webapp3.service.MonitorService;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;


@Controller
public class AdminController {


    @Autowired
    private ActivityService actServ;

    @Autowired
    private MonitorService monServ;

    //Statistics of monitor

    @GetMapping("/statistics")
    public String statistics(Model model){
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
                                 @RequestParam String price, @RequestParam String description,
                                 @RequestParam int capacity, @RequestParam String monday,
                                 @RequestParam String tuesday, @RequestParam String wednesday,
                                 @RequestParam String thursday, @RequestParam String friday, @RequestParam("image") MultipartFile image) throws IOException {
        Activity activity = new Activity(name, price, description, room, capacity, monday, tuesday, wednesday, thursday, friday);
        activity.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
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
                                 @RequestParam String price, @RequestParam String description,
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
            activity.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
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
            if (activity.getImageFile() != null){
                Resource file = new InputStreamResource(activity.getImageFile().getBinaryStream());

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(activity.getImageFile().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/activity/delete/{id}")
    public String deleteActivity(Model model, @PathVariable long id){
        actServ.delete(id);
        return "redirect:/activities";
    }

    //Monitors' management
    @GetMapping("/monitors")
    public String monitors(Model model){
        model.addAttribute("monitors",monServ.findAll());
        return "USRADM_03MonitorsList";
    }

    @GetMapping("/addNewMonitor")
    public String newMonitor(Model model){
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
                                @RequestParam String activityName,
                                @RequestParam String description,
                                @RequestParam("image") MultipartFile image) throws IOException {
        DateType birthday = new DateType(birthdayDate.substring(0, 4), birthdayDate.substring(5, 7), birthdayDate.substring(8, 10));
        DateType hiring = new DateType(hiringDate.substring(0, 4), hiringDate.substring(5, 7), hiringDate.substring(8, 10));
        Monitor monitor = new Monitor(name, surname, NIF, email, NIF, address, postalCode, phone, birthday,
                true, hiring, activityName, description);
        monitor.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
        monServ.save(monitor);

        return "redirect:/monitors";
    }

    @GetMapping("/monitor/{id}")
    public String seeMonitorInfo(Model model, @PathVariable Long id){
        Optional<Monitor> optMonitor = monServ.findById(id);

        String html = "";
        if (optMonitor.isPresent()){
            model.addAttribute("monitor", optMonitor.get());
            html = "USRADM_10SeeMonitorInfo";
        } else {
            html = "USRADM_03MonitorsList";
        }
        return html;
    }

    @GetMapping("/monitor/{id}/image")
    public ResponseEntity<Object> downloadMonitorImage(@PathVariable long id) throws SQLException{
        Optional<Monitor> optMon = monServ.findById(id);

        if (optMon.isPresent()){
            Monitor monitor = optMon.get();
            if (monitor.getImageFile() != null){
                Resource file = new InputStreamResource(monitor.getImageFile().getBinaryStream());

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .contentLength(monitor.getImageFile().length()).body(file);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/editMonitor/{id}")
    public String editMonitor(Model model, @PathVariable Long id){
        Optional<Monitor> optMonitor = monServ.findById(id);

        String html = "";
        if (optMonitor.isPresent()){
            model.addAttribute("monitor", optMonitor.get());
            html = "USRADM_11EditMonitor";
        } else {
            html = "USRADM_03MonitorsList";
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
                                   @RequestParam String activityName,
                                   @RequestParam String description,
                                   @RequestParam("image") MultipartFile image) throws IOException {
        Optional<Monitor> mon = monServ.findById(id);
        String htmlFile;
        if (mon.isPresent()){
            Monitor monitor = mon.get();
            monitor.setName(name);
            monitor.setSurname(surname);
            monitor.setNIF(NIF);
            monitor.setEmail(email);
            monitor.setAddress(address);
            monitor.setPostalCode(postalCode);
            monitor.setPhone(phone);
            monitor.getBirthday().setDay(birthdayDate.substring(8, 10));
            monitor.getBirthday().setMonth(birthdayDate.substring(5, 7));
            monitor.getBirthday().setYear(birthdayDate.substring(0,4));
            monitor.getBirthday().generateSpanishFormat();
            monitor.getHiringDate().setDay(hiringDate.substring(8, 10));
            monitor.getHiringDate().setMonth(hiringDate.substring(5, 7));
            monitor.getHiringDate().setYear(hiringDate.substring(0, 4));
            monitor.getHiringDate().generateSpanishFormat();
            monitor.setActivityName(activityName);
            monitor.setDescription(description);
            monitor.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
            monServ.save(monitor);
            htmlFile = "redirect:/monitors";
        } else {
            //Gestionar error envío de formulario
            htmlFile = "error-404";
        }
        return htmlFile;
    }

    @GetMapping("/delete/monitor/{id}")
    public String deleteMonitor(Model model, @PathVariable long id){
        monServ.delete(id);
        return "redirect:/monitors";
    }

    //Clients' management
    @GetMapping("/clients")
    public String clients(Model model){
        /*List<Client> all = usrServ.findAll();
        model.addAttribute("clientList", all);*/
        return "USRADM_12Clients";
    }

    //Gestionar error
    @GetMapping("/error")
    public String error(Model model){
        return "error-404";

    }
}
