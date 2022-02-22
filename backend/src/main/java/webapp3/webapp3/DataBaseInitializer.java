package webapp3.webapp3;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

public class DataBaseInitializer {

    @Autowired
    private ActivityRepository actRep;

    @PostConstruct
    public void init() throws IOException, URISyntaxException {

        Activity act1 = new Activity("Zumba", "22.60",
                "Actividad de cardio de baile.", "Sala multiusos 1",
                23, "16:00-17:00");
        setActivityImage(act1, "");
        actRep.save(act1);

        Activity act2 = new Activity("BodyPump", "25.60",
                "Actividad de cardio de puñetazos al aire.", "Sala multiusos 2",
                15, "16:00-17:00");
        setActivityImage(act2, "");
        actRep.save(act2);

        Activity act3 = new Activity("Body Combat", "18.50",
                "Actividad de cardio de combate.", "Sala multiusos 1",
                23, "17:30-18:30");
        setActivityImage(act3, "");
        actRep.save(act3);

        Activity act4 = new Activity("Spinning", "10.00",
                "Actividad de cardio de bicicleta estatica con música.", "Sala multiusos 2",
                15, "17:30-18:30");
        setActivityImage(act4, "");
        actRep.save(act4);

        Activity act5 = new Activity("Yoga", "5.00",
                "Actividad de relajación y posturistas.", "Sala de yoga",
                5, "20:00-21:00");
        setActivityImage(act5, "");
        actRep.save(act5);

    }

    public void setActivityImage(Activity activity, String classpathResource) throws IOException {
        activity.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        activity.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}
