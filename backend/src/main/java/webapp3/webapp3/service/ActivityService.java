package webapp3.webapp3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.repository.ActivityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    //Properties

    @Autowired
    private ActivityRepository activityRep;

    //Methods
    public Optional<Activity> findById(Long id){
        return activityRep.findById(id);
    }

    public List<Activity> findAll(){
        return activityRep.findAll();
    }

    public void delete(Long id){
        activityRep.deleteById(id);
    }

    public Activity save(Activity act){
        return activityRep.save(act);
    }

    public Activity findByName(String name){
        return activityRep.findByName(name);
    }

}
