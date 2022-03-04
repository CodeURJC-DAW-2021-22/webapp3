package webapp3.webapp3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.repository.ExerciseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRep;

    public Optional<Exercise> findById(Long id){
        return exerciseRep.findById(id);
    }

    public List<Exercise> findAll(){
        return exerciseRep.findAll();
    }

    public void delete(Long id){
        exerciseRep.deleteById(id);
    }

    public Exercise save(Exercise ex){
        return exerciseRep.save(ex);
    }
}
