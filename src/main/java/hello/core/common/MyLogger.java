package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    // 해당 클래스는 각 컨트롤러 및 Service에서 파라미터가 지저분해지지 않기 위해 구현해놓은 클래스이다.

    private String uuid;
    private String reqeustURL;

    public void setReqeustURL(String reqeustURL) {
        this.reqeustURL = reqeustURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + reqeustURL + "]" + "[" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
