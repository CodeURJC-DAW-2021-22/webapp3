package webapp3.webapp3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.repository.ActivityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    public Optional<Activity> findById(long id){
        return repository.findById(id);
    }

    public boolean exist(long id){
        return repository.existsById(id);
    }

    public List<Activity> findAll() {
        return repository.findAll();
    }

    public void save(Activity activity) {
        repository.save(activity);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
