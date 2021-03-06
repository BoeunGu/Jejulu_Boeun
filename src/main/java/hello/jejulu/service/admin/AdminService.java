package hello.jejulu.service.admin;

import hello.jejulu.web.dto.host.HostDto;
import hello.jejulu.web.dto.member.MemberDto;
import hello.jejulu.web.dto.post.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AdminService {
    Page<MemberDto.AdminDetail> getMembersForAdmin(Pageable pageable);
    Page<HostDto.AdminDetail> getHostsForAdmin(Pageable pageable);
    Page<PostDto.AdminDetail> getPostsForAdmin(Pageable pageable);
}
