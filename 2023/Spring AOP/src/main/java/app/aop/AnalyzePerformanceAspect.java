package app.aop;

import lombok.Data;
import lombok.Getter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class AnalyzePerformanceAspect {
    @Getter
    private final Map<String, Metrics> memo = new ConcurrentHashMap<>();

    @Around("@target(AnalyzePerformance)")
    public Object analyze(ProceedingJoinPoint jp) throws Throwable {
        var key = jp.getSignature().toString();
        var start = System.nanoTime();
        try {
            return jp.proceed();
        }
        finally {
            memo.computeIfAbsent(key, k -> new Metrics()).update(System.nanoTime() - start);
        }
    }

    @Data
    public static class Metrics {
        private long totalNano;
        private int invocationCount;

        public void update(long timeElapsedNano) {
            totalNano += timeElapsedNano;
            invocationCount++;
        }

        @Override
        public String toString() {
            return "Metrics{" +
                    "totalNano=" + totalNano +
                    ", invocationCount=" + invocationCount +
                    ", avg=" + ((double)totalNano / invocationCount) +
                    '}';
        }
    }
}
