package com.sammidev.demo.intro.introFlux;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {

    // LEARN TO WRITE JUNIT TEST

    @Test
    public void fluxTest() {
        Flux<String> stringFlux = Flux.just("sammidev","adisti","aditya")
//                .concatWith(Flux.error(new RuntimeException("exception")))
                .concatWith(Flux.just("after error")
                .log());

        stringFlux
                .subscribe(System.out::println,
                        (e) -> System.err.println(e),
                        () -> System.out.println("complete"));
    }

    @Test
    public void fluxTestElement_WithoutError() {
        Flux<String> stringFlux = Flux.just("sammidev","adisti","aditya")
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("sammidev")
                .expectNext("adisti")
                .expectNext("aditya")
                .verifyComplete();
    }

    @Test
    public void fluxTestElement_WithError() {
        Flux<String> stringFlux = Flux.just("sammidev","adisti","aditya")
                .concatWith(Flux.error(new RuntimeException("exception bro")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("sammidev")
                .expectNext("otong")
                .expectNext("surotong")
                 //.expectError(RuntimeException.class)
                .expectErrorMessage("exception bro")
                .verify();}

    // Flux -> Learn to Write Junit TEST
    @Test
    public void fluxTestElementCount_WithError() {
        Flux<String> stringFlux = Flux.just("sammidev","otong","surotong")
                .concatWith(Flux.error(new RuntimeException("exception bro")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectErrorMessage("exception bro")
                .verify();}

    @Test
    public void fluxTestElement_WithError1() {
        Flux<String> stringFlux = Flux.just("sammidev","otong","surotong")
                .concatWith(Flux.error(new RuntimeException("exception bro")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("sammidev","otong","surotong")
                .expectErrorMessage("exception bro")
                .verify();}}
