package co.vikingos.performance.web_reactive.controller;


import co.vikingos.performance.web_reactive.model.TestData;
import co.vikingos.performance.web_reactive.repository.TestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TestController {

  @Autowired
  private TestDataRepository testDataRepository;

  @GetMapping("/api/")
  public Flux<TestData> getTestData() {
    return this.testDataRepository.findAll();
  }

}