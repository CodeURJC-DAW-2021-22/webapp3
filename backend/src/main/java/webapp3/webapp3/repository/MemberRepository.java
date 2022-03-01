package webapp3.webapp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import webapp3.webapp3.model.Member;

@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<Member, Long> {
}