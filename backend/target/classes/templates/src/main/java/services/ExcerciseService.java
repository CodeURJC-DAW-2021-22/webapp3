package services;

import java.util.List;
import java.util.Optional;

import model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ExcerciseRepository;

@Service
public class ExcerciseService {

    @Autowired
    private ExcerciseRepository repository;

    public Optional<Exercise> findById(long id){
        return repository.findById(id);
    }

    public boolean exist(long id) {
        return repository.existsById(id);
    }

    public List<Exercise> findAll() {
        return repository.findAll();
    }

    public void save(Exercise exercise) {
        repository.save(exercise);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

}
