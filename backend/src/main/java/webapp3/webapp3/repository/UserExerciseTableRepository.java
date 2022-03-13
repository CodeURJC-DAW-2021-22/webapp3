package webapp3.webapp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webapp3.webapp3.model.Exercise;
import webapp3.webapp3.model.ExerciseTable;
import webapp3.webapp3.model.User;
import webapp3.webapp3.model.UserExerciseTable;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;

public interface UserExerciseTableRepository extends JpaRepository<UserExerciseTable, Long> {


    List<UserExerciseTable> findByUser(User us);

    @Query(value = "SELECT EXERCISE_TABLE_ID FROM USER_EXERCISE_TABLE WHERE USER_ID = :user GROUP BY EXERCISE_TABLE_ID ORDER BY COUNT(EXERCISE_TABLE_ID) DESC", nativeQuery = true)
    List<Long> findTopExercises(Long user);

    @Query(value = "SELECT count(EXERCISE_TABLE_ID) FROM USER_EXERCISE_TABLE GROUP BY EXERCISE_TABLE_ID ORDER BY COUNT(EXERCISE_TABLE_ID) DESC", nativeQuery = true)
    List<Integer> findHowMany();
}
