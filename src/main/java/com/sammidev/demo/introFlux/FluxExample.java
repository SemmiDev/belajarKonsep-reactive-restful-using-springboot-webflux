package com.sammidev.demo.introFlux;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class FluxExample {

    // FLUX -> HOW IT WORKS

    @Test
    public void fluxTest() {

        Flux.just("Sam","Adit","Dandi","Gusnur","Rauf")
                .map(val -> val.concat("Lebih bessa lagi dei!!"))
                .subscribe(System.out::println);

        // example 1
        Flux<String> stringFlux = Flux.just("sammidev","aditya","Ayatullah");
        stringFlux.subscribe(System.out::println);


        // example 2
        Flux<String> stringFlux2 = Flux.just("sammidev","aditya","adisti")
                .concatWith(Flux.error(new RuntimeException("oh no exception occured")));
        stringFlux2.subscribe(System.out::println,
                (e) -> System.err.println(e));

        // example 3
        Flux<String> stringFlux3 = Flux.just("sammidev","aditya","adisti")
                .concatWith(Flux.error(new RuntimeException("oh no exception occured")))
                .log(); // show all event
        stringFlux3.subscribe(System.out::println,
                (e) -> System.err.println(e));

        // example 4
        Flux<String> stringFlux4 = Flux.just("Sam","Adit","Gusnur", "Dandi", "Rauf", "Ayatul")
                .concatWith(Flux.just("After ERROR"))
                .log(); // show all event
        stringFlux4.subscribe
                (System.out::println,
                (e) -> System.err.println(e),
                () -> System.out.println("complete"));
    }
}
