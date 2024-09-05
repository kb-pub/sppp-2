package rx.repo;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import rx.domain.Temperature;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class StubTemperatureRepository implements TemperatureRepository {
    private final Random random = new Random();
    @Override
    public Flux<Temperature> findAll() {
        return Flux.just(1,2,3,4,5)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> new Temperature(LocalDateTime.now(), 1000 + random.nextGaussian() * 100));
    }
}
