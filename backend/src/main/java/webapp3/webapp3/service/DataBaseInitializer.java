package webapp3.webapp3.service;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.DateType;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.model.Member;
import webapp3.webapp3.repository.ActivityRepository;
import webapp3.webapp3.repository.ExerciseRepository;
import webapp3.webapp3.repository.MemberRepository;


@Service
public class DataBaseInitializer {

    @Autowired
    private ExerciseRepository exRep;

    @Autowired
    private MemberRepository memRep;

    @Autowired
    private ActivityRepository actRep;

    @PostConstruct
    public void init() throws IOException, URISyntaxException {


        Exercise ex1 = new Exercise("Ejercicios Básicos");
        setExerciseImage(ex1, "/static/img/gallery/gallery-10.jpeg");
        exRep.save(ex1);

        Exercise ex2 = new Exercise("1000");
        setExerciseImage(ex2, "/static/img/gallery/gallery-11.jpeg");
        exRep.save(ex2);

        Exercise ex3 = new Exercise("Día a día");
        setExerciseImage(ex3, "/static/img/gallery/gallery-12.jpeg");
        exRep.save(ex3);

        Exercise ex4 = new Exercise("Ejercicios sin fechas");
        setExerciseImage(ex4, "/static/img/gallery/gallery-13.jpeg");
        exRep.save(ex4);

        DateType d = new DateType("2000","03","08");

        Member memb1 = new Member("Andrea", "Nuzzi Herrero", "@AndyNuzzi", "AndyNuzzi00",
                "andy.nuzzi@gmail.com", "1111111D", d, "Mujer", 160, List.of(2, 3, 5, 6),
                "C/Aranjuez", "28914", "000-99-87-54", "0000-44543-66775-33",
                "No tengo lesiones ni alergias conocidas");
        memRep.save(memb1);

        Activity act1 = new Activity("Zumba");
        setActivityImage(act1, "/static/img/gallery/gallery-6.jpg");
        actRep.save(act1);

        Activity act2 = new Activity("BodyPump");
        setActivityImage(act2, "/static/img/gallery/gallery-4.jpg");
        actRep.save(act2);

        Activity act3 = new Activity("BodyCombat");
        setActivityImage(act3, "/static/img/gallery/gallery-1.jpg");
        actRep.save(act3);

        Activity act4 = new Activity("Spinning");
        setActivityImage(act4, "/static/img/gallery/gallery-7.jpg");
        actRep.save(act4);

        Activity act5 = new Activity("Yoga");
        setActivityImage(act5, "/static/img/gallery/gallery-2.jpg");
        actRep.save(act5);

    }

    public void setExerciseImage(Exercise exercise, String classpathResource) throws IOException {
        exercise.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        exercise.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setActivityImage(Activity activity, String classpathResource) throws IOException {
        activity.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        activity.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}