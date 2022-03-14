package webapp3.webapp3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webapp3.webapp3.model.Activity;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserType(String userType);

    //public User findByName(String name);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    @Query(
            value = "SELECT COUNT(ID) FROM USER_TABLE WHERE USER_TYPE = :type AND TO_CHAR(ENTRY_DATE, 'YYYY-MM-DD') LIKE :date%",
            nativeQuery = true)
    int findByUserTypeAndEntryDate(String type,@Param("date") String date);

    Page<User> findByUserType(Pageable page, String userType);

}
