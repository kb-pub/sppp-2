package app;

import app.aop.AnalyzePerformanceAspect;
import app.bean.A;
import app.bean.B;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.stream.IntStream;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@RequiredArgsConstructor
public class Main {
    private final AnalyzePerformanceAspect analyzePerformanceAspect;
    private final B b;

    public void run() {
        IntStream.range(0, 10000).forEach(i -> b.m());

        var b1 = new B(new A());
        var avg = IntStream.range(0, 10000).mapToLong(i -> {
            var start = System.nanoTime();
            b1.m();
            return System.nanoTime() - start;
        }).average();

        System.out.println(analyzePerformanceAspect.getMemo());
        System.out.println(avg);
    }

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Main.class).getBean(Main.class).run();
    }
}