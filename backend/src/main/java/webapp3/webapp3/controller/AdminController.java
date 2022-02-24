package webapp3.webapp3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.service.ActivityService;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private ActivityService actServ;

    @GetMapping("/statistics")
    public String statistics(Model model){
        return "USRADM_01Statistics";
    }

    @GetMapping("/activities")
    public String activities(Model model){
        List<Activity> all = actServ.findAll();
        model.addAttribute("activitiesList", all);
        return "USRADM_02ActivitiesList";
    }

    @GetMapping("/monitors")
    public String monitors(Model model){
        /*List<Monitor> all = monServ.findAll();
        model.addAttribute("monitorsList", all);*/
        return "USRADM_03MonitorsList";
    }

    @GetMapping("/clients")
    public String clients(Model model){
        /*List<Client> all = usrServ.findAll();
        model.addAttribute("clientList", all);*/
        return "USRADM_12Clients";
    }

    @GetMapping("/addNewActivity")
    public String newActivity(Model model){
        return "USRADM_04AddActivity";
    }

    @PostMapping("/addNewActivity")
    public String addNewActivity(Model model, @RequestParam String name, @RequestParam String room,
                                 @RequestParam String price, @RequestParam String description,
                                 @RequestParam int capacity, @RequestParam String schedule){
        actServ.save(new Activity(name, price, description, room, capacity, schedule));
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
                                 @RequestParam int capacity, @RequestParam String schedule, @PathVariable Long id){
        Optional<Activity> act = actServ.findById(id);
        String htmlFile;
        if (act.isPresent()){
            Activity activity = act.get();
            activity.setName(name);
            activity.setRoom(room);
            activity.setPrice(price);
            activity.setDescription(description);
            activity.setCapacity(capacity);
            activity.setSchedule(schedule);
            actServ.save(activity);
            htmlFile = "redirect:/activities";
        } else {
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


    //Gestionar error
    @GetMapping("/error")
    public String error(Model model){
        return "error-404";
    }
}
