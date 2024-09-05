package rx.event;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import rx.domain.Temperature;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class TemperatureStream {
    private final Sinks.Many<Temperature> emitter = Sinks.many().multicast().directBestEffort();
    private final Random random = new Random();

    @Scheduled(initialDelay = 2, fixedRate = 2, timeUnit = TimeUnit.SECONDS)
    public void emit() {
        var t = new Temperature(LocalDateTime.now(), 1000 + random.nextGaussian() * 100);
        var result = emitter.tryEmitNext(t);
        System.out.println("EMITTER emit " + t + ", result = " + result);
    }

    public Flux<Temperature> get() {
        return emitter.asFlux();
    }
}
