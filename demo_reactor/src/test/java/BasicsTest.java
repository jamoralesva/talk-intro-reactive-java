import java.util.ArrayList;
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
  public void FluxBasicTest() {
    Flux.range(1, 100)
        .publishOn(Schedulers.parallel())
        .subscribe(v -> System.out.println(v));
  }

  @Test
  public void FluxHotTest(){
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
  public void FluxTest() {
    List<Integer> squares = new ArrayList<>();
    Flux.range(1, 64).flatMap(v ->
        Mono.just(v)
            .subscribeOn(Schedulers.newSingle("comp"))
            .map(w -> w * w))
        .doOnError(ex -> ex.printStackTrace())
        .doOnComplete(() -> System.out.println("Completed"))
        .subscribeOn(Schedulers.immediate())
        .subscribe(squares::add);
  }

  @Test
  public void FluxBackPressureTest() {
    List<Integer> squares = new ArrayList<>();
    Flux.range(1, 64) // 1
        .onBackpressureBuffer(256).map(v -> v * v) // 3
        .subscribeOn(Schedulers.immediate())
        .subscribe(squares::add); // 4

    System.out.println(squares);

    //assertArrayEquals(squares, );
  }

}
