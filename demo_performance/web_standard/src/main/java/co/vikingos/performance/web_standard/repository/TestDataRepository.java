package co.vikingos.performance.web_standard.repository;

import co.vikingos.performance.web_standard.model.TestData;
import org.springframework.data.repository.CrudRepository;

public interface TestDataRepository extends CrudRepository<TestData, Long> {
}