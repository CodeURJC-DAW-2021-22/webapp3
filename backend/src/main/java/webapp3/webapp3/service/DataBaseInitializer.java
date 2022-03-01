package webapp3.webapp3.service;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.DateType;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.model.Member;
import webapp3.webapp3.repository.ExerciseRepository;
import webapp3.webapp3.repository.MemberRepository;


@Service
public class DataBaseInitializer {

    @Autowired
    private ExerciseRepository exRep;

    @Autowired
    private MemberRepository memRep;

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

        DateType d = new DateType("2000","03","08");

        Member memb1 = new Member("Andrea", "Nuzzi Herrero", "@AndyNuzzi", "AndyNuzzi00",
                "andy.nuzzi@gmail.com", "1111111D", d, "Mujer", "160", "55",
                "C/Aranjuez", "28914", "000-99-87-54", "0000-44543-66775-33",
                "No tengo lesiones ni alergias conocidas");
        memRep.save(memb1);

    }

    public void setExerciseImage(Exercise exercise, String classpathResource) throws IOException {
        exercise.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        exercise.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }
}