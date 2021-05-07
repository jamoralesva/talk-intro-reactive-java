package co.demo.talk.reactive.basic;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class StepVerifierDemo {

  @Test
  public void simpleStepVerifierDemo(){
    Flux<String> source = generateFixture()
        .filter(name -> name.length() == 4)
        .map(String::toUpperCase);

    StepVerifier
        .create(source)
        .expectNext("JOHN")
        .expectNextMatches(name -> name.startsWith("MA"))
        .expectNext("CLOE", "CATE")
        .expectComplete()
        .verify();
  }

  @Test
  public void exceptionStepVerifierDemo(){
    Flux<String> error = generateFixture()
        .filter(name -> name.length() == 4)
        .concatWith(
          Mono.error(new IllegalArgumentException("Our message"))
        );


    StepVerifier
        .create(error)
        .expectNextCount(4)
        .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException &&
            throwable.getMessage().equals("Our message")
        ).verify();
  }

  private Flux<String> generateFixture(){
    return Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate");
  }

}
