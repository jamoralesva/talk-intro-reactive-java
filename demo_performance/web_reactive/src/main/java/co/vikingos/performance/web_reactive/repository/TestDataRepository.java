package co.vikingos.performance.web_reactive.repository;

import co.vikingos.performance.web_reactive.model.TestData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TestDataRepository extends ReactiveCrudRepository<TestData, Long> {
}
