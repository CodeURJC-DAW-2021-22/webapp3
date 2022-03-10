package webapp3.webapp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webapp3.webapp3.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByUserType(String userType);

    public User findByName(String name);

    @Query(
            value = "SELECT COUNT(ID) FROM USER WHERE USER_TYPE = :type AND ENTRY_DATE LIKE :date%",
            nativeQuery = true)
    public int findByUserTypeAndEntryDate(String type, String date);
}
