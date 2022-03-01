package webapp3.webapp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp3.webapp3.model.Monitor;

public interface MonitorRepository extends JpaRepository<Monitor, Long> {
}
