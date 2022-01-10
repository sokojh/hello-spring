package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/*그냥 이상태로 테스트를 돌리면 각각 오류가 안나던것들이 한번에 테스트하니깐 findByName()에서 오류가 나게된다
 왜?? 이미 findAll을 실행할때 repositroy에 Member객체가 저장되었기 때문에 findByname에서 생성된 Member객체랑 달라지기 때문이다.*/

class MemoryMemberReopositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    //콜백 메서드라고 생각하면 된다.
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);

    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();
        assertThat(result).isEqualTo(member2);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
