package hello2.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "String";
        System.out.println("name:" + name);

        log.trace(" trace log={}", name);

        log.trace(" trace log={}", name);   //상태야
        log.debug(" debug log={}", name);   //개발서버
        log.info("  info log={}", name);    // 중요정보
        log.warn("  warn log={}", name);    //경고야
        log.error(" error log={}", name);   //에러야
        return "ok";
    }

}
