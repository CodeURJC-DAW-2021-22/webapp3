package webapp3.webapp3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Exercise;

import webapp3.webapp3.repository.ExerciseRepository;


import java.util.List;
import java.util.Optional;


@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository repository;

    public Optional<Exercise> findById(long id){
        return repository.findById(id);
    }

    public boolean exist(long id){
        return repository.existsById(id);
    }

    public List<Exercise> findAll() {
        return repository.findAll();
    }

    public void save(Exercise activity) {
        repository.save(activity);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public Page<Exercise> findPage(int page){
        return repository.findAll(PageRequest.of(page, 10));
    }
}
