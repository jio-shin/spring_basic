package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;


    //외부에서 넣어줄 수 있도록 변경 -> test시 같은 memberRepository 사용가능함
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //회원가입
    public Long join(Member member) {
    //같은 이름인 중복 회원 가입 안된다
       /* Optional<Member> result = memberRepository.findByName(member.getName());

        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        아래처럼 나타낼 수도 있다. */

        //ctrl alt shift v 해서 extract하면 이렇게 나타낼 수 있다.
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId(); //임의로 아이디 받는 것
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
              .ifPresent(m -> {
                  throw new IllegalStateException("이미 존재하는 회원입니다.");
              });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
