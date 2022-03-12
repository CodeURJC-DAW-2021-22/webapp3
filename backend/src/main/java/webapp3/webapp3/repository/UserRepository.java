package webapp3.webapp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webapp3.webapp3.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByUserType(String userType);

    //public User findByName(String name);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    public int findByUserTypeAndEntryDate(String type, String date);

}
