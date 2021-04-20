package co.vikingos.performance.web_reactive.repository;

import co.vikingos.performance.web_reactive.model.TestData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TestDataRepository extends ReactiveMongoRepository<TestData, String> {
}