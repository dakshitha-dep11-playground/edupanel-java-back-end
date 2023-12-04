package lk.ijse.dep11.edupanel;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebAppConfig {
    @Bean
    public StandardServletMultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

    @Bean
    public Bucket defaultBucket() throws IOException {
//        FileInputStream serviceAccount =
//                new FileInputStream("path/to/serviceAccountKey.json");

        InputStream serviceAccount = getClass().getResourceAsStream("/edu-panel-e7a92-firebase-adminsdk-t4rpq-3c01dfcfe3.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("edu-panel-e7a92.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);

        Bucket bucket = StorageClient.getInstance().bucket();
        return bucket;
    }
}
