package hello.jejulu;

import hello.jejulu.domain.host.Host;
import hello.jejulu.domain.util.Role;
import hello.jejulu.service.host.HostServiceImpl;
import hello.jejulu.service.post.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TestDateHost {

    private final HostServiceImpl hostService;
    private final PostServiceImpl postService;

   // @Transactional
    //@PostConstruct
    public void init() throws IOException {
      Host join = hostService.join(new Host(99L, "test_host", "111111", "보은이", "제주특별자치도 제주시 구좌읍 계룡길 5", "rnqhdms@gmail.com", "010-2202-6380", Role.HOST, null));

      
    }



}
