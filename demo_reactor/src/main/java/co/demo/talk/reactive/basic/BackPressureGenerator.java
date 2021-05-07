package co.demo.talk.reactive.basic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink.OverflowStrategy;

public class BackPressureGenerator {

  public static Flux<Object> generate(int max, OverflowStrategy strategy) {
    return Flux.create(emitter -> {
      // Publish 1000 numbers
      for (int i = 0; i < max; i++) {
        System.out.println(Thread.currentThread().getName() + " | Publishing = " + i);
        emitter.next(i);
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      emitter.complete();
    }, strategy);
  }

}
