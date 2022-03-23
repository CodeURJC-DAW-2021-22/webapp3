package webapp3.webapp3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.model.User;
import webapp3.webapp3.model.UserExerciseTable;
import webapp3.webapp3.repository.ExerciseTableRepository;
import webapp3.webapp3.repository.UserExerciseTableRepository;

import java.util.*;

@Service
public class UserExerciseTableService {

    @Autowired
    private ExerciseTableRepository exTabRep;

    @Autowired
    private UserExerciseTableRepository repository;

    public boolean exist(long id){ return repository.existsById(id); }

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

    public TreeMap<String, Integer> findExercisesTables(Long id){
        List<Long> topExercises = repository.findTopExercises(id);
        TreeMap<String, Integer> map = new TreeMap<>();
        List<Integer> rep = repository.findHowMany();
        while (!topExercises.isEmpty()){
            Long removed = topExercises.remove(0);
            map.put(exTabRep.findById(removed).orElseThrow().getName(), rep.remove(0));
        }
        return map;
    }
}
