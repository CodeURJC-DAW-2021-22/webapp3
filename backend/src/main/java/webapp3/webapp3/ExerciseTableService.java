package webapp3.webapp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseTableService {
    @Autowired
    private ExerciseTableRepository repository;

    public Optional<ExerciseTable> findById(Long id){
        return repository.findById(id);
    }

    public boolean exist(long id) {
        return repository.existsById(id);
    }

    public List<ExerciseTable> findAll() {
        return repository.findAll();
    }

    public void save(ExerciseTable ej) {
        repository.save(ej);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}