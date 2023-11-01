package rx.repo;

import reactor.core.publisher.Flux;
import rx.domain.Temperature;

public interface TemperatureRepository {
    Flux<Temperature> findAll();
}
