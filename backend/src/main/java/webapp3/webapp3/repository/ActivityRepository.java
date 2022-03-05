package webapp3.webapp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webapp3.webapp3.model.Activity;

import java.util.ArrayList;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    public Activity findByName(String name);

    public ArrayList<Activity> findByMonitorName(String s);
}
