package services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ExerciseTable;
import repository.ExcerciseTableRepository;


@Service
public class ExcerciseTableService {

    @Autowired
    private ExcerciseTableRepository repository;

    public Optional<ExerciseTable>findByid (long id){
        return repository.findById(id);
    }

    public boolean exist(long id) {
        return repository.existsById(id);
    }

    public List<ExerciseTable> findAll() {
        return repository.findAll();
    }

    public void save(ExerciseTable exerciseTable) {
        repository.save(exerciseTable);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

}


