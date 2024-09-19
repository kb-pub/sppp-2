package app.bean;

import app.aop.AnalyzePerformance;
import app.aop.Logging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@AnalyzePerformance
@Service
@RequiredArgsConstructor
public class B {
    private final A a;

//    @Logging
    public void m() {
//        System.out.println("hello from B.m()");
        a.m1();
        a.m2();
        m2();
    }

    @Logging
    public void m2() {
//        System.out.println("hello from B.m2()");
//        for (var c : new Throwable().getStackTrace()) {
//            System.out.println(c.getClassName() + "::" + c.getMethodName());
//        }
    }
}
