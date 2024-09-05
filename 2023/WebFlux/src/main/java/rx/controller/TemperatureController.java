package rx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import rx.domain.Temperature;
import rx.event.TemperatureStream;
import rx.repo.TemperatureRepository;

@RestController
@RequiredArgsConstructor
public class TemperatureController {
    private final TemperatureRepository temperatureRepository;
    private final TemperatureStream temperatureStream;

    @GetMapping("/temp/all")
    public Flux<Temperature> findAll() {
        return temperatureRepository.findAll();
    }

    @GetMapping("/temp/stream")
    public Flux<ServerSentEvent<String>> stream() {
        return temperatureStream.get()
                .map(t -> ServerSentEvent.<String>builder()
                        .event("temperature-stream")
                        .data(t.toString())
                        .build())
                .doOnNext(System.out::println);
    }
}
