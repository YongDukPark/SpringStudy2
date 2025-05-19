package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-memo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {

        //주입받은 오브젝트 호출
        MyLogger myLogger = myLoggerProvider.getObject();

        //고객의 요청 URL 가져오기
        String requestURL = request.getRequestURL().toString();
        myLogger.setReqeustURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
