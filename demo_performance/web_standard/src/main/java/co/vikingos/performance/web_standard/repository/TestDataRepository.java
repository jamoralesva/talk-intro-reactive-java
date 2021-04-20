package co.vikingos.performance.web_standard.repository;

import co.vikingos.performance.web_standard.model.TestData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestDataRepository extends MongoRepository<TestData, String> {
}