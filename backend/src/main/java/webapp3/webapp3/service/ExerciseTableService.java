package webapp3.webapp3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.repository.ExerciseTableRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseTableService {

    @Autowired
    private ExerciseTableRepository exerciseTabRep;

    public Optional<ExerciseTable> findById(Long id){
        return exerciseTabRep.findById(id);
    }

    public List<ExerciseTable> findAll(){
        return exerciseTabRep.findAll();
    }

    public void delete(Long id){
        exerciseTabRep.deleteById(id);
    }

    public ExerciseTable save(ExerciseTable exTab){
        return exerciseTabRep.save(exTab);
    }
}
