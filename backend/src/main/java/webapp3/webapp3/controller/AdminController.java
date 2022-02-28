package webapp3.webapp3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.DateType;
import webapp3.webapp3.model.Monitor;
import webapp3.webapp3.service.ActivityService;
import webapp3.webapp3.service.MonitorService;

import java.util.List;
import java.util.Optional;

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
                                 @RequestParam String thursday, @RequestParam String friday){
        actServ.save(new Activity(name, price, description, room, capacity, monday, tuesday, wednesday, thursday, friday));
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
                                    @RequestParam String thursday, @RequestParam String friday, @PathVariable Long id){
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
                                @RequestParam String description){
        DateType birthday = new DateType(birthdayDate.substring(0, 2), birthdayDate.substring(3, 5), birthdayDate.substring(6, 10));
        DateType hiring = new DateType(hiringDate.substring(0, 2), hiringDate.substring(3, 5), hiringDate.substring(6, 10));
        monServ.save(new Monitor(name, surname, NIF, email, NIF, address, postalCode, phone, birthday,
                true, hiring, activityName, description));

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
                                   @RequestParam String description){
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
            DateType birthday = new DateType(birthdayDate.substring(0, 2), birthdayDate.substring(3, 5), birthdayDate.substring(6, 10));
            DateType hiring = new DateType(hiringDate.substring(0, 2), hiringDate.substring(3, 5), hiringDate.substring(6, 10));
            monitor.setBirthday(birthday);
            monitor.setHiringDate(hiring);
            monitor.setActivityName(activityName);
            monitor.setDescription(description);
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
