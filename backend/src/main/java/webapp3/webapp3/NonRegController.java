package webapp3.webapp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class NonRegController {

    //Test BBDD References
    @Autowired
    private MonitorPruebaService serviceM;
    @Autowired
    private ActivityPruebaService serviceA;

    //Test data
    @PostConstruct
    public void ini(){
        serviceM.save(new MonitorPrueba("Fernando"));
        serviceM.save(new MonitorPrueba("Carlos"));
        serviceM.save(new MonitorPrueba("Federico"));
    }

    //Main page
    @GetMapping("/USR_mainpage")
    public String mainPage(Model model){
        List<MonitorPrueba> monitores = serviceM.findAll();
        model.addAttribute("monitor", monitores);

        return "USR_01mainPage";
    }

    @GetMapping("/USR_mainpage/{id}/image")
    public ResponseEntity<Object> showMonitorImage(@PathVariable long id) throws SQLException {
        Optional<MonitorPrueba> monitor = serviceM.findById(id);

        if (monitor.isPresent() && monitor.get().getImageFile() != null) {

            Resource file = new InputStreamResource(monitor.get().getImageFile().getBinaryStream());

            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(monitor.get().getImageFile().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
