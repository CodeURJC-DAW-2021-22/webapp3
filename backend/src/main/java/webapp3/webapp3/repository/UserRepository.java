package webapp3.webapp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserType(String userType);

    //public User findByName(String name);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    @Query(
            value = "SELECT COUNT(ID) FROM USER WHERE USER_TYPE = :type AND ENTRY_DATE LIKE :date%",
            nativeQuery = true)
    int findByUserTypeAndEntryDate(String type, String date);

    @Query(
            value = "SELECT COUNT(ID) FROM USER WHERE USER_TYPE = :type AND ENTRY_DATE LIKE :date%",
            nativeQuery = true)
    int findByUserTypeAndDownloads(String type, String date);


    /*

    public User findByName(String name);

    */

}
