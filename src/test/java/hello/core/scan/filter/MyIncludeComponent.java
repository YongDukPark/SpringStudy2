package hello.core.scan.filter;

import java.lang.annotation.*;

// TYPE은 클래스레벨에 붙는거다.
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
