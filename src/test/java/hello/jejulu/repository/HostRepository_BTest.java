package hello.jejulu.repository;

import hello.jejulu.domain.host.Host;
import hello.jejulu.domain.util.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class HostRepository_BTest {

    @Autowired HostRepository_B hostRepositoryB;

  //  @Autowired EntityManager entityManager;

    @Test
    void 호스트_회원가입_테스트(){

        //given

        Host host = new Host(1L, "test_host", "111111",
                "보은이", "제주특별자치도 제주시 구좌읍 계룡길 5", "rnqh@ggggg.com",
                "010-5555-6666", Role.HOST, null);


        //when
        hostRepositoryB.save(host);
        Optional<Host> test_host = hostRepositoryB.findByLoginId("test_host");

        //then
        Assertions.assertThat(host.getLoginId()).isEqualTo(test_host.orElse(null).getLoginId());


    }

}