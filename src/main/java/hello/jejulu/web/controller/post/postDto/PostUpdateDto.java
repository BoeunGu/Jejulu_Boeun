package hello.jejulu.web.controller.post.postDto;

import hello.jejulu.domain.post.Post;
import hello.jejulu.domain.thumbnail.Thumbnail;
import hello.jejulu.domain.util.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Builder
@Getter@Setter
public class PostUpdateDto {

    /**
     * Update 스펙(View에서 필요한 정보)
     */

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    //@NotBlank
    private Category category;


    private Thumbnail thumbnail;

    @NotBlank
    private String content;

    public static PostUpdateDto updatePost(Post post){
        return PostUpdateDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .category(post.getCategory())
                .thumbnail(post.getThumbnail())
                .content(post.getContent())
                .build();
    }
}
