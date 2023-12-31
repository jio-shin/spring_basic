package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //repository에 회원이 저장되는 기능
    Member save(Member member);

    //찾아오는 기능
    Optional<Member> findById(Long id);
    Optional<Member>findByName(String name);
    List<Member> findAll();
}
