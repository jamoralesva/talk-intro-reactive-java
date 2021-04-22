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

  static String[] first_names = {"Piter", "Yeison", "Rigoberto", "Albeiro", "Eider", "Didier", "Stevens", "Edinson", "Yonier", "Esneider"};
  static String[] last_names = {"Paniagua", "Pechene", "Anacona", "Cristancho", "Chacano", "Tumerque", "Cali", "Guaman", "Guaita", "Gonzalez"};

  @ChangeSet(order = "001", id = "seedDatabase_random", author = "jamv")
  public void seedDatabase(TestDataRepository repository) {
    for (int i = 0; i < 1000; i++) {
      repository.insert(generate100());
    }
  }

  private TestData createItem(String name, BigDecimal age) {
    TestData testData = new TestData();
    testData.setAge(age);
    testData.setName(name);
    return testData;
  }
  private int random(){
    return (int)(Math.random() * 10);
  }

  private String generateName(){
     return first_names[random()] + " " +
            first_names[random()] + " " +
            last_names[random()] + " " + last_names[random()];
  }

  private TestData randomItem(){
    return createItem(generateName(), BigDecimal.valueOf(random()));
  }

  private List<TestData> generate100(){
    List<TestData> list = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      list.add(randomItem());
    }
    return list;
  }
}