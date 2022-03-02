package services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.GroupActivity;
import repository.GroupActivityRepository;


@Service
public class GroupActivitiy {

    @Autowired
    private GroupActivityRepository repository;

    public Optional<GroupActivity>findByid (long id){
        return repository.findById(id);
    }

    public boolean exist(long id) {
        return repository.existsById(id);
    }

    public List<GroupActivity> findAll() {
        return repository.findAll();
    }

    public void save(GroupActivity groupActivity) {
        repository.save(groupActivity);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }


}
