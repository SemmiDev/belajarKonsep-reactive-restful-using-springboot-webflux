package com.sammidev.demo.intro.introMono;

import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoTest {
    public static void main(String[] args) {
        Mono.just("Ayatullah Ramdhan Jacoeb")
                .map(val -> val.concat("memang sangad bessa"))
                .subscribe(System.out::println);
    }


    Mono<String> stringMono =  Mono.just("Sam");
    //.log -> for get logger = how it work

    @Test
    public void monoTest() {
        StepVerifier.create(stringMono.log())
                .expectNext("Sam")
                .verifyComplete();
    }

    @Test
    public void monoTesterror() {
        StepVerifier.create(Mono.error(new RuntimeException("exception bro")))
                .expectError(RuntimeException.class)
                .verify();
    }
}