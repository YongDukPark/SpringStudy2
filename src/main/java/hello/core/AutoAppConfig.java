package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // classes = Configuration.class <- 해당 어노테이션이 붙은 클래스는 빼겠다는 뜻이다.
        excludeFilters =  @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)

public class AutoAppConfig {



}
