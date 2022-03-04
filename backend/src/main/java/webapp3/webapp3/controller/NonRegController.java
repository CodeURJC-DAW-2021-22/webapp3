package webapp3.webapp3.controller;


import org.hibernate.type.DateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.Monitor;
import webapp3.webapp3.service.ActivityService;
import webapp3.webapp3.service.MonitorService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Controller
public class NonRegController {


    //BBDD References
    @Autowired
    private ActivityService actServ;
    @Autowired
    private MonitorService monServ;


    //-------------------------------------------------Main page------------------------------------------------------//

    //Main page controller
    @GetMapping("/USR_mainpage")
    public String mainPage(Model model){
        List<Monitor> monitores = monServ.findAll();
        model.addAttribute("monitor", monitores);

        return "USR_01mainPage";
    }

    //Monitor images download for main page
    @GetMapping("/USR_mainpage/{id}/image")
    public ResponseEntity<Object> showMonitorImage(@PathVariable long id) throws SQLException {
        Optional<Monitor> monitor = monServ.findById(id);

        if (monitor.isPresent() && monitor.get().getImageFile() != null) {

            Resource file = new InputStreamResource(monitor.get().getImageFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(monitor.get().getImageFile().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //-------------------------------------------------Activities page------------------------------------------------//

    //Activities page controller
    @GetMapping("/USR_activities")
    public String activities(Model model){
        List<Activity> actividades = actServ.findAll();
        model.addAttribute("activities", actividades);

        return "USR_02activities";
    }

    //Activities image download
    @GetMapping("/USR_activities/{id}/image")
    public ResponseEntity<Object> showActivitieImage(@PathVariable long id) throws SQLException {
        Optional<Activity> actividad = actServ.findById(id);

        if (actividad.isPresent() && actividad.get().getImageFile() != null) {

            Resource file = new InputStreamResource(actividad.get().getImageFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(actividad.get().getImageFile().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //-------------------------------------------------Schedule page--------------------------------------------------//

    //Schedule page controller
    @GetMapping("/USR_activities/schedule/{id}")
    public String schedule(Model model, @PathVariable long id){
        Optional<Activity> act = actServ.findById(id);
        String result;
        if(act.isPresent()){
            result = "USR_03schedule";
            model.addAttribute("name", act.get().getName());
            model.addAttribute("description", act.get().getDescription());
            if(!act.get().getMonday().equals("")){
                model.addAttribute("monAct", true);
                model.addAttribute("monday", act.get().getMonday());
            }
            if(!act.get().getTuesday().equals("")){
                model.addAttribute("tusAct", true);
                model.addAttribute("tuesday", act.get().getTuesday());
            }
            if(!act.get().getWednesday().equals("")){
                model.addAttribute("wenAct", true);
                model.addAttribute("wednesday", act.get().getWednesday());
            }
            if(!act.get().getThursday().equals("")){
                model.addAttribute("thuAct", true);
                model.addAttribute("thursday", act.get().getThursday());
            }
            if(!act.get().getFriday().equals("")){
                model.addAttribute("friAct", true);
                model.addAttribute("friday", act.get().getFriday());
            }
        }else{
            result = "404";
        }
        return result;
    }

    //Activities image download for schedule
    @GetMapping("/USR_activities/schedule/{id}/image")
    public ResponseEntity<Object> scheduleImage(@PathVariable long id) throws SQLException{
        Optional<Activity> actividad = actServ.findById(id);

        if (actividad.isPresent() && actividad.get().getImageFile() != null) {

            Resource file = new InputStreamResource(actividad.get().getImageFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(actividad.get().getImageFile().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //-------------------------------------------------Prices page----------------------------------------------------//

    //Prices page controller
    @GetMapping("/USR_prices")
    public String price(Model model){return "USR_04prices";}

    //-------------------------------------------------Contact us page------------------------------------------------//

    //Contact us page controller
    @GetMapping("/USR_contact_us")
    public String contacUs(Model model){return "USR_05contact_us";}

    //-------------------------------------------------Log in page----------------------------------------------------//

    //Log in page controller
    @GetMapping("/USR_log_in")
    public String loginPage(Model model){return "USR_06log_in";}

    @RequestMapping("/USR_log_in")
    public String login(){return "USR_06log_in";}

    @RequestMapping("/USR_log_inError")
    public String loginErr(){return "/USR_08log_inError";}

    //-------------------------------------------------Sign in page---------------------------------------------------//

    //Sign in page controller
    @GetMapping("/USR_sign_in")
    public String signin(Model model){return "USR_07sign_in";}

    //Add user
    @PostMapping("/USR_sign_in")
    public String postSignin(Model model,@RequestParam String name, @RequestParam String surname, @RequestParam String NIF, @RequestParam DateType birthday,
                             @RequestParam String phone_num, @RequestParam String postal_code, @RequestParam String address, @RequestParam String email,
                             @RequestParam String password, @RequestParam DateType entryDate, @RequestParam int height, @RequestParam int weight,
                             @RequestParam String IBAN, @RequestParam String medicalInfo){
        //añadir Server.save cuando este la base de datos
        return "redirect:/USR_06log_in";
    }
}

