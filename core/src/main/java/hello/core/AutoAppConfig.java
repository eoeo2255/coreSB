package hello.core; //  탐색 범위를 지정하지 않으면 해당 범위로 지정됨

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core",    //  탐색 범위 지정, 여러개 가능
        basePackageClasses = AutoAppConfig.class,   //  탐색 범위를 클래스로 한정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //  제외 대상
)
public class AutoAppConfig {



}
