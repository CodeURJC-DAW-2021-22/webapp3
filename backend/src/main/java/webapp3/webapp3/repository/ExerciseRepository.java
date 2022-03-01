package webapp3.webapp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp3.webapp3.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise,Long>{}