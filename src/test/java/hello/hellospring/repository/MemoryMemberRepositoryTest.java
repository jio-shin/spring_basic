package hello.hellospring.repository;

import hello.hellospring.domain.Member;
/*import org.junit.jupiter.api.Assertions;*//*
import org.assertj.core.api.Assertions;*/
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test는 순서상관없이 작동해서 하나의 테스트가 끝나면 repository를제거해주는 게 필요함
    @AfterEach
    public void afterEach() {
        repository.clearstore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        //아이디 저장할 때 (save) 아이디 세팅됨.
        repository.save(member);

        //optional에서 꺼낼 때는 get()사용함.
        Member result = repository.findById(member.getId()).get();


        //검증방법 0.result 가 member랑 같은지 검증
        // System.out.println("result = " + (result == member));

        //검증방법 1.Assertions.assertEquals 사용하기 -> 초록불뜨면 같은 것
        // Assertions.assertEquals(member,result);}

        //검증방법 2.Assrtions.assertThat
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //Optional<Member> result = repository.findByName("spring1");
        //.get()하면 Optional를 까서 꺼낼 수 있다!
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}