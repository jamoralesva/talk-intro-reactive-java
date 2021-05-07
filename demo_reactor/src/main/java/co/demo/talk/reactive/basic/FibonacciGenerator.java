package co.demo.talk.reactive.basic;

import java.util.concurrent.atomic.AtomicBoolean;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

public class FibonacciGenerator {

  public static Flux<Long> generateFlux() {
     return Flux.generate(
        () -> Tuples.<Long, Long>of(0L, 1L), //función que inicializa el estado para cada suscriptor
        (state, sink) -> {          //función que se llama cada vez que hay una solicitud, recibe el estado y un sink existente por cada suscriptor
          //el sink (sumidero) permite emitir una señal a la vez al suscriptor subyacente
          sink.next(state.getT1());
          return Tuples.of(state.getT2(), state.getT1() + state.getT2());
        });
  }

  public static Flux<Long> generateFluxWithSink() {
    return Flux.create(e -> {
      long current = 1, prev = 0;
      AtomicBoolean stop = new AtomicBoolean(false);
      e.onDispose(()->{
        stop.set(true);
        System.out.println("******* Stop Received ****** ");
      });
      while (current > 0) {
        e.next(current);
        System.out.println("generated " + current);
        long next = current + prev;
        prev = current;
        current = next;
      }
      e.complete();
    });
  }



}
