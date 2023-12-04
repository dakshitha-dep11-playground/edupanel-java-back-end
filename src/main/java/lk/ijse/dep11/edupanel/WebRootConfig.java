package lk.ijse.dep11.edupanel;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class WebRootConfig {
    @Bean(destroyMethod = "close")
    public HikariDataSource dataSource(Environment env){
        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(env.getRequiredProperty("spring.datasource.url"));
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/dep11_edupanel");
//        hikariConfig.setUsername(env.getRequiredProperty("spring.datasource.username"));
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("7575");
//        hikariConfig.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class"));
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        hikariConfig.setMaximumPoolSize(env.getRequiredProperty("spring.datasource.hikari.maximum-pool-size", Integer.class));
        hikariConfig.setMaximumPoolSize(10);
        return new HikariDataSource(hikariConfig);
    }



}
