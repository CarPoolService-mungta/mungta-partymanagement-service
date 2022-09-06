package partymanagement.config;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

//RestController 사용 시 CORS 문제 발생하는 경우 사용한 설정 (WebMvcConfigurer 만으로 안되는 경우)
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getCorsRegistry()
                .addMapping("/**")      //패턴
            .allowedOrigins("*") //URL
            .allowedMethods("*")         //method
            .allowedHeaders("*")       //Header
            .allowCredentials(true)
            .maxAge(3000);
    }
}

