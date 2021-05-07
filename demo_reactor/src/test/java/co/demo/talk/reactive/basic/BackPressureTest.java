package co.demo.talk.reactive.basic;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.FluxSink.OverflowStrategy;
import reactor.core.scheduler.Schedulers;

public class BackPressureTest {

  @Test
  public void dropTest() throws InterruptedException {
    BackPressureGenerator
        .generate(1000, OverflowStrategy.DROP)
        .onBackpressureDrop(i -> System.out.println(Thread.currentThread().getName() + " | DROPPED = " + i))
        .subscribeOn(Schedulers.boundedElastic())
        .publishOn(Schedulers.boundedElastic())
        .subscribe(i -> {
            // Se procesa el valor recibido
            System.out.println(Thread.currentThread().getName() + " | Received = " + i);
            // simular un suscriptor lento
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
        });

    // subscribeOn & publishOn - Pone el suscriptor y publicador en diferentes hilos

    // se mantiene el hilo principal despierto por 100 seconds.
    Thread.sleep(100000);

  }


  @Test
  public void bufferTest() throws InterruptedException {
    BackPressureGenerator
        .generate(100000, OverflowStrategy.BUFFER)
        .subscribeOn(Schedulers.elastic())
        .publishOn(Schedulers.elastic())
        .onBackpressureBuffer(2)
        .subscribe(
          i -> {
            // Se procesa el valor recibido
            System.out.println(Thread.currentThread().getName() + " | Received = " + i);
            // simular un suscriptor lento
            try {
              Thread.sleep(500);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          },
          e -> {
            // Process error
            System.err.println(Thread.currentThread().getName() + " | Error = " + e.getClass().getSimpleName() + " " + e.getMessage());
          });


    // subscribeOn & publishOn - Pone el suscriptor y publicador en diferentes hilos

    // se mantiene el hilo principal despierto por 100 seconds.
    Thread.sleep(100000);

  }

}
