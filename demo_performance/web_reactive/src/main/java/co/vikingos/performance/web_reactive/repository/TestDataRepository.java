package co.vikingos.performance.web_reactive.repository;

import co.vikingos.performance.web_reactive.model.TestData;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TestDataRepository extends ReactiveMongoRepository<TestData, String> {
  @Query("{'age': ?0}")
  Flux<TestData> findByAge(String age);
}