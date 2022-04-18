package hello.jejulu.service.post;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import hello.jejulu.domain.thumbnail.Thumbnail;
import hello.jejulu.repository.ThumbnailRepository_B;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThumbnailService_B {

    private final ThumbnailRepository_B thumbnailRepositoryB;

    //썸네일 조회
    public Thumbnail findThumbnail(String id){
        Thumbnail thumbnail = thumbnailRepositoryB.find(id);

        return thumbnail;
    }



    //썸네일 생성
    @Transactional
    public Thumbnail createThumbnail(MultipartFile file) throws IOException {
        String imagePath = uploadImage(file);
        Thumbnail thumbnail = new Thumbnail(createStoreFileName(file.getOriginalFilename()),
                imagePath, file.getOriginalFilename());
        String thumbnailId = thumbnailRepositoryB.save(thumbnail);
        Thumbnail thumbnail1 = thumbnailRepositoryB.find(thumbnailId);

        return thumbnail1;
    }

    //썸네일 업데이트
    @Transactional
    public String updateThumbnail(MultipartFile file,Thumbnail thumbnail) throws IOException{

        if(file.isEmpty()){
            return thumbnail.getId();
        }else {

            thumbnail.updateEntityThumbnail(uploadImage(file), file.getOriginalFilename());

        }
        return thumbnail.getId();


    }


    //썸네일 삭제
    @Transactional
    public void deleteThumbnail(String id){
        thumbnailRepositoryB.delete(id);
    }



        //path
        public  String uploadImage(MultipartFile file) throws IOException {
            Bucket bucket = StorageClient.getInstance().bucket("jejulu-3b679.appspot.com");
            String originFileName = file.getOriginalFilename();
            String storeFileName = createStoreFileName(originFileName);
            InputStream image = new ByteArrayInputStream(file.getBytes());
            Blob blob = bucket.create("thumbnail/" + storeFileName, image, file.getContentType());
            return getFirebaseImagePath(blob.getMediaLink()); //구글-> 파이어베이스스토리지로 URL 변경함
        }


        //고유한 저장이름 생성 -> id
        private String createStoreFileName(String originFileName) {
            String uuid = UUID.randomUUID().toString();
            String extendsName = extracted(originFileName);//확장자명 추출
            return uuid + "." + extendsName;
        }


        //확장자명 추출
        private String extracted(String originFileName) {
            int pos = originFileName.lastIndexOf(".");
            return originFileName.substring(pos + 1);
        }


        //이미지경로 호출 -> path
        private String getFirebaseImagePath(String meadiaLink) {
            int start = meadiaLink.lastIndexOf("%");
            int last = meadiaLink.lastIndexOf("?");
            return "https://firebasestorage.googleapis.com/v0/b/jejulu-3b679.appspot.com/o/thumbnail%"
                    + meadiaLink.substring(start + 1, last)
                    + "?alt=media";
        //}


    }


}
