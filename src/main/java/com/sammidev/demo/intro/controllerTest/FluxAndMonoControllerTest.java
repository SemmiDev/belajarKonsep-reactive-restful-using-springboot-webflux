package com.sammidev.demo.intro.controllerTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebFluxTest
public class FluxAndMonoControllerTest {

    @Autowired
    WebTestClient webTestClient;


    @Test
    public void flux_approach() {
       Flux<Integer> flux =  webTestClient.get().uri("/flux")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus().isOk()
        .returnResult(Integer.class)
        .getResponseBody();

        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();
    }

    @Test
    public void flux_approach2() {
        webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Integer.class)
                .hasSize(3);
    }

    @Test
    public void flux_approach3() {

        List<Integer> expected = Arrays.asList(1,2,3);

         EntityExchangeResult<List<Integer>> entityExchangeResult =
                 webTestClient.get().uri("/flux")
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBodyList(Integer.class)
                    .returnResult();

         assertEquals(expected,entityExchangeResult.getResponseBody());
    }


    @Test
    public void flux_approach4() {

        List<Integer> expected = Arrays.asList(1,2,3);

        webTestClient.get().uri("/flux")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class)
                .consumeWith(response -> {
                   assertEquals(expected,response.getResponseBody());
                });
    }





    @Test
    public void fluxStream2() {
        Flux<Long> longStreamFlux =  webTestClient.get().uri("/fluxStream2")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Long.class)
                .getResponseBody();

        StepVerifier.create(longStreamFlux)
                .expectNext(0l)
                .expectNext(1l)
                .expectNext(2l)
                .thenCancel()
                .verify();
    }




    // MONO

    @Test
    public void mono() {
        Integer expectedValue = new Integer(1);

        webTestClient.get().uri("/mono")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith((response) -> {
                    assertEquals(expectedValue,response.getResponseBody());
                });
    }
}