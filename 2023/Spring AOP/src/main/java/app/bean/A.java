package app.bean;

import app.aop.Logging;
import org.springframework.stereotype.Service;

@Service
public class A {
    @Logging
    public void m1() {
//        System.out.println("hello from A.m1()");
    }

    @Logging
    public void m2() {
//        System.out.println("hello from A.m2()");
    }
}
