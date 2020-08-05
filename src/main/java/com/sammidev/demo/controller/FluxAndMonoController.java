package com.sammidev.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class FluxAndMonoController {

    // wait sampai semua selesai kemudian baru di tampilkan
    @GetMapping("/flux")
    public Flux<Integer> returnFlux() {
        return Flux.just(1,2,3)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    // render as a stream = muncul setiap on next, setiap 2 detik
    @GetMapping(value = "/fluxStream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> returnFluxStream() {
        return Flux.just(1,2,3)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    // render continious -> berkelanjutan seperti increment
    @GetMapping(value = "/fluxStream2", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long> returnFluxStream2() {
        return Flux.interval(Duration.ofSeconds(1))
                .log();
    }



    // MONO

    @GetMapping("/mono")
    public Mono<Integer> returnMono() {
        return Mono.just(1)
                .log();
    }
    
}