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
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.repository.ExerciseRepository;


@Service
public class DataBaseInitializer {

    @Autowired
    private ExerciseRepository exRep;

    @PostConstruct
    public void init() throws IOException, URISyntaxException {


        Exercise ex1 = new Exercise("Ejercicios Básicos");
        exRep.save(ex1);

        Exercise ex2 = new Exercise("1000");
        exRep.save(ex2);

        Exercise ex3 = new Exercise("Día a día");
        exRep.save(ex3);

        Exercise ex4 = new Exercise("Ejercicios sin fechas");
        exRep.save(ex4);

    }

    public void setActivityImage(Exercise exercise, String classpathResource) throws IOException {
        exercise.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        exercise.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}