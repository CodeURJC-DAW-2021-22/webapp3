package webapp3.webapp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapp3.webapp3.model.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {
}