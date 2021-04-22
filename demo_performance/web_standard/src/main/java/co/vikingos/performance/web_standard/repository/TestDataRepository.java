package co.vikingos.performance.web_standard.repository;

import co.vikingos.performance.web_standard.model.TestData;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TestDataRepository extends MongoRepository<TestData, String> {
  @Query("{'age': ?0}")
  List<TestData> findByAge(String age);
}