package co.vikingos.performance.web_standard.config;

import co.vikingos.performance.web_standard.model.TestData;
import co.vikingos.performance.web_standard.repository.TestDataRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangeLog {

  @ChangeSet(order = "001", id = "seedDatabase", author = "jamv")
  public void seedDatabase(TestDataRepository repository) {
    List<TestData> expenseList = new ArrayList<>();
    expenseList.add(createItem("Piter Albeiro", BigDecimal.valueOf(40)));
    expenseList.add(createItem("Ricardo Quevedo", BigDecimal.valueOf(60)));
    expenseList.add(createItem("Polilla", BigDecimal.valueOf(10)));
    expenseList.add(createItem("La Bruja Dioselina", BigDecimal.valueOf(20)));
    expenseList.add(createItem("Otro", BigDecimal.valueOf(30)));

    repository.insert(expenseList);
  }

  private TestData createItem(String name, BigDecimal age) {
    TestData testData = new TestData();
    testData.setAge(age);
    testData.setName(name);
    return testData;
  }
}