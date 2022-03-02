package services;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import model.*;
import repository.*;

@Service
public class DataBaseInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExcerciseRepository excerciseRepository;

    @Autowired
    private  ExcerciseTableRepository excerciseTableRepository;

    @Autowired
    private  GroupActivityRepository groupActivityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws IOException,URISyntaxException{}

    public void setUserImage(User user, String classpathResource) throws IOException {
        user.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        user.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setExcerciseTableImage(ExerciseTable exerciseTable, String classpathResource) throws IOException {
        exerciseTable.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        exerciseTable.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }

    public void setExcerciseImage(Exercise exercise, String classpathResource) throws IOException {
        exercise.setImage(true);
        Resource image = new ClassPathResource(classpathResource);
        exercise.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
    }



}
