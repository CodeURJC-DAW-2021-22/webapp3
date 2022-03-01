package webapp3.webapp3.service;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.Client;
import webapp3.webapp3.model.DateType;
import webapp3.webapp3.model.Monitor;
import webapp3.webapp3.repository.ActivityRepository;
import webapp3.webapp3.repository.ClientRepository;
import webapp3.webapp3.repository.MonitorRepository;

@Service
public class DataBaseInitializer {

    @Autowired
    private ActivityRepository actRep;

    @Autowired
    private MonitorRepository monRep;

    @Autowired
    private ClientRepository clnRep;

    @PostConstruct
    public void init() throws IOException, URISyntaxException {

        Activity act1 = new Activity("Zumba","21.00", "Nestor crack", "Sala 1", 12, "12:00","11:00","10:00",
                "9:00", "10:00");
        setActivityImage(act1, "/sample_images/gallery-8.jpg");
        actRep.save(act1);

        Activity act2 = new Activity("BodyPump","21.00", "Nestor crack", "Sala 1", 12, "12:00","11:00","10:00",
                "9:00", "10:00");
        setActivityImage(act2, "/sample_images/gallery-3.jpg");
        actRep.save(act2);

        Activity act3 = new Activity("BodyCombat","21.00", "Nestor crack", "Sala 1", 12, "12:00","11:00","10:00",
                "9:00", "10:00");
        setActivityImage(act3, "/sample_images/gallery-2.jpg");
        actRep.save(act3);

        Activity act4 = new Activity("Spinning","21.00", "Nestor crack", "Sala 1", 12, "12:00","11:00","10:00",
                "9:00", "10:00");
        setActivityImage(act4, "/sample_images/gallery-4.jpg");
        actRep.save(act4);

        Activity act5 = new Activity("Yoga","21.00", "Posturitas", "Sala 1", 12, "12:00","11:00","10:00",
                "9:00", "10:00");
        setActivityImage(act5, "/sample_images/gallery-5.jpg");
        actRep.save(act5);


        DateType d = new DateType("1993", "12", "03");

        Monitor mon1 = new Monitor("Jose Luis","Martinez Munuera",
                "11111111A", "mm@gmail.com","PRUEBA", "PRUEBA", "21131",
                "43534534", d, false, d, "Spinning", "Larga trayectoria profesional en el mundo fitness.");
        setMonitorImage(mon1, "/sample_images/Monitor1.jpeg");
        monRep.save(mon1);

        Monitor mon2 = new Monitor("Jose David","Fernandez Dgank",
                "11111111B", "am@gmail.com","PRUEBA", "PRUEBA", "21344",
                "43532344", d, false,d, "Yoga", "Yogui como el oso.");
        setMonitorImage(mon2, "/sample_images/Monitor2.jpeg");
        monRep.save(mon2);

        Monitor mon3 = new Monitor("Maria Luisa","Rodriguez Carajote",
                "11111111C", "rc@gmail.com","PRUEBA", "PRUEBA", "74553",
                "423489789", d, false,d, "Body Pump", "Te miro y te golpeo.");
        setMonitorImage(mon3, "/sample_images/Monitor3.jpeg");
        monRep.save(mon3);

        Client c1 = new Client("A1", "a1", 16, "11112222A");
        clnRep.save(c1);
        Client c2 = new Client("A2", "a2", 17, "11112222B");
        clnRep.save(c2);
        Client c3 = new Client("A3", "a3", 18, "11112222C");
        clnRep.save(c3);
        Client c4 = new Client("A4", "a4", 19, "11112222D");
        clnRep.save(c4);
    }

    public void setActivityImage(Activity activity, String classpathResource) throws IOException {
        activity.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        activity.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setMonitorImage(Monitor monitor, String classpathResource) throws IOException {
        monitor.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        monitor.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
