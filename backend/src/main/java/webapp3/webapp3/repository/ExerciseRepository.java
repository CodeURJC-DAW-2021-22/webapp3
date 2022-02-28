package webapp3.webapp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import webapp3.webapp3.model.Exercise;

@EnableJpaRepositories
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
