package webapp3.webapp3.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import webapp3.webapp3.model.ExerciseTable;

public interface ExerciseTableRepository extends JpaRepository<ExerciseTable, Long> {

    Page<ExerciseTable> findAll(Pageable page);

}
