package co.demo.talk.reactive.basic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class OperatorTest {

  /**
  Pruebas de operadores básicos de preprocesamiento
   */
  @Test
  public void flatMapTest(){
    List<Integer> squares = new ArrayList<>();
    Flux.range(1, 64)
        .flatMap(v ->
            Mono.just(v)
                .subscribeOn(Schedulers.newSingle("comp")) //ejecuta la tarea en nuevo hilo
                .map(w -> w * w))
        .doOnError(ex -> ex.printStackTrace())
        .doOnNext(i -> System.out.println(i))
        .doOnComplete(() -> System.out.println("Completed"))
        .subscribeOn(Schedulers.immediate())
        .subscribe(squares::add);

    System.out.println(squares);

  }

  @Test
  public void filterTest(){
    FibonacciGenerator.generateFlux()
                      .take(10)
                      .filter(a -> a%2 == 0) //probamos la paridad
                      .subscribe(t -> {
                          System.out.println(t);
                      });
  }

  @Test
  public void collectMapTest(){
    FibonacciGenerator.generateFlux()
        .take(5)
        .collectMap(t -> t%2==0 ? "even": "odd") //recoge todos los datos, pero mapea los mas recientes
        .subscribe(t -> {
          System.out.println(t);
        });
  }


  @Test
  public void delayTest() throws InterruptedException {
    FibonacciGenerator.generateFlux()
        .take(10)
        .delayElements(Duration.ofSeconds(1))
        .subscribe(t -> {
          System.out.println(t);
        });
    //probar poniendo este y quitandolo
    Thread.sleep(10000);
  }
}
