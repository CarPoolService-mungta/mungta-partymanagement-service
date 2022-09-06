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

// @Component
// public class RepositoryConfig
//     implements RepositoryRestConfigurer {

//   @Override
//   public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

//     cors.addMapping("/*")
//         .allowedOrigins("*")
//         .allowedMethods("GET", "PUT", "DELETE")
//         .allowCredentials(false).maxAge(3600);
//   }
// }
/*
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

}*/
//이거 써보던지.
@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
    //이걸로도 먹힌다.
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

// @Configuration
// public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

//     @Override
//     public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//         config.getCorsRegistry()
//         .addMapping("/**")      //패턴
//         .allowedOrigins("*") //URL
//         .allowedMethods("*")         //method
//         .allowedHeaders("*")       //Header
//         .allowCredentials(true)
//         .maxAge(3000);
//      }
// }


/*spring-data-rest-webmvc 의
 configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors)를 쓰면 되는데
 라이브러리 충돌나서 해결 못함
그래서 일단 deprecated된 adapter 쓰기로함.

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        final Class<?>[] classes = this.entityManager.getMetamodel()
            .getEntities()
            .stream()
            .map(Type::getJavaType)
            .toArray(Class[]::new);
        config.exposeIdsFor(classes);
    }
}
*/
