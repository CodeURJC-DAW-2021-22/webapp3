package webapp3.webapp3.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp3.webapp3.model.User;
import webapp3.webapp3.repository.UserRepository;

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

    public User findByName(String name){
        return userRep.findByName(name).orElseThrow();
    }
}
