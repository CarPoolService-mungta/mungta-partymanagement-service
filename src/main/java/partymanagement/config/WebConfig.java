package partymanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
//Gateway Port로 사용 시
//gateway yml에 cors 설정이 들어가 있어서 CORS 중복 에러가 발생하여 제거함.
//[ERROR] The 'Access-Control-Allow-Origin' header contains multiple values
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")      //패턴
    //             .allowedOrigins("*") //URL
    //             .allowedMethods("*")         //method
    //             .allowedHeaders("*")       //Header
    //             .allowCredentials(true)
    //             .maxAge(3000);
    // }
}
