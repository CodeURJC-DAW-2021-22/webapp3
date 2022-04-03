package webapp3.webapp3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.model.User;
import webapp3.webapp3.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRep;

    public Optional<User> findById(Long id){
        return userRep.findById(id);
    }

    public List<User> findAll(){
        return userRep.findAll();
    }

    public void delete(Long id){
        userRep.deleteById(id);
    }

    public User save(User ex){
        return userRep.save(ex);
    }

    public List<User> findByUserType(String userType) {
        return userRep.findByUserType(userType);
    }

    public Optional<User> findByName(String name){
        return userRep.findByName(name);
    }

    public boolean exist(long id) {return userRep.existsById(id);}


    // generate data for graphics
    public int findByUserTypeAndEntryDate(String type, int month, String year) {
        String s = Integer.toString(month);
        if (s.length() == 1)
            s = "0" + s;
        return userRep.findByUserTypeAndEntryDate(type, year + "-" + s + "-");
    }

    public Optional<User> findByEmail(String prueba) {
        return userRep.findByEmail(prueba);
    }

    public Page<User> findPageClient(int page, String s){
        return userRep.findByUserType(PageRequest.of(page, 3), s);
    }

    public ArrayList<ArrayList<Integer>> getStatistics() {
        String [] years = {"2019", "2020", "2021", "2022"};
        ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            listOfLists.add(i, new ArrayList<Integer>(12));
        }

        ArrayList<Integer> yearComp = new ArrayList<>(12);

        int j = 0;
        for (String year : years) {
            for (int i = 0; i < 12; i++) {
                yearComp.add(this.findByUserTypeAndEntryDate("member", i + 1, year));
            }
            listOfLists.get(j++).addAll(yearComp);
            yearComp.clear();
        }

        return listOfLists;
    }
}
