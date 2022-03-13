package webapp3.webapp3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.model.User;
import webapp3.webapp3.model.UserExerciseTable;
import webapp3.webapp3.repository.UserExerciseTableRepository;

import java.util.*;

@Service
public class UserExerciseTableService {

    @Autowired
    private UserExerciseTableRepository repository;

    public boolean exist(long id){
        return repository.existsById(id);
    }

    public List<UserExerciseTable> findAll() {
        return repository.findAll();
    }

    public void save(UserExerciseTable userExerciseTable) {
        repository.save(userExerciseTable);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<UserExerciseTable> findbyUser(User user) {
        return repository.findByUser(user);
    }

    public Map<Long, Integer> findExercisesTables(User user){
        return repository.findTopExercises(user);
    }
}
