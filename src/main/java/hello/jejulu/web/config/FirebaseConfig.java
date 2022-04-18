package hello.jejulu.web.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Configuration
public class FirebaseConfig {

    @Value("${firebase.config}")
    private String configFile;

    @Value("${firebase.bucket}")
    private String bucket;

    //Bean 등록
    @PostConstruct
    public void init(){
        try{
            //파이어베이스 설정
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new FileInputStream(configFile))) //권한셋팅
                    .setStorageBucket(bucket)
                    .build();

            if(FirebaseApp.getApps().isEmpty()){
                //해당권한으로 파이버베이스 만들기
                FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
                log.info("FirebaseApp has been initialized {}",firebaseApp);
            }
        } catch (IOException e){
            log.error(e.getMessage());
        }
    }
}
