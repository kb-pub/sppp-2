package app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("@annotation(app.aop.Logging)")
    public void log(JoinPoint jp) {
        System.out.println("LOG: called " + jp.getSignature());
    }
}
