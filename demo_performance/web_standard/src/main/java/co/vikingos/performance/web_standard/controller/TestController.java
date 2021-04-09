package co.vikingos.performance.web_standard.controller;


import co.vikingos.performance.web_standard.model.TestData;
import co.vikingos.performance.web_standard.repository.TestDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @Autowired
  private TestDataRepository testDataRepository;

  @GetMapping("/api/")
  public Iterable<TestData> getTestData() {
    return this.testDataRepository.findAll();
  }
}