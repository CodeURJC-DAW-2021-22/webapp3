package repository;
import model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExcerciseRepository extends JpaRepository<Exercise,Long> {

}
