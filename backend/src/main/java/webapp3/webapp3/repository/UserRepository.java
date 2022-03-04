package webapp3.webapp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp3.webapp3.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByUserType(String userType);

    public User findByName(String name);
}
