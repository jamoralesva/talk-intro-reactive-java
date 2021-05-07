package co.demo.talk.reactive.basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 4.2 Revisión de los componentes básicos
 */

public class BasicsTest {


  @Test
  public void fluxBasicTest() {
    Flux.range(1, 100)
        .publishOn(Schedulers.parallel())
        .subscribe(v -> System.out.println(v));
  }

  @Test
  public void simpleColdFluxTest() {
    StringBuilder str = new StringBuilder();
    Flux<String> stingFlux = Flux.just("Quick", "brown", "fox", "jumped", "over", "the", "wall");
    stingFlux.subscribe(t -> {
      str.append(t).append(" ");
    });
    assertEquals("Quick brown fox jumped over the wall ", str.toString());
  }

  @Test
  public void simpleHotfluxTest(){
    Flux<Long> squares = Flux.generate(
        AtomicLong::new, // Se pone como Supplier el constructor de AtomicLong
        (state, sink) -> {
          long i = state.getAndIncrement();
          sink.next(i * i); // Luego de incrementar se pasa al observador el cuadrado del número
          if (i == 10) sink.complete(); // Condición de parada
          return state;
        });

    squares.subscribe(v -> System.out.println(v));
  }

  @Test
  public void fibonacciGeneratorTest() {

    List<Long> fibonacciSeries = new LinkedList<>();
    int size = 50;
    FibonacciGenerator.generateFlux()
        .take(size)
        .subscribe(t -> {
          fibonacciSeries.add(t);
        });
    System.out.println(fibonacciSeries);
    assertEquals( 7778742049L, fibonacciSeries.get(size-1).longValue());
  }

  @Test
  public void fibonacciFluxSinkTest() {
    List<Long> fibonacciSeries = new LinkedList<>();
    FibonacciGenerator.generateFluxWithSink()
        .take(10)
        .subscribe(t -> {
          System.out.println("consuming " + t);
          fibonacciSeries.add(t);
        });
    System.out.println(fibonacciSeries);
  }

}
