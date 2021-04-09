package co.vikingos.performance.web_standard.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "testdata")
public class TestData {

  @PrimaryKey
  private Long id;

  @Column(value = "someText")
  private String someText;

  public String getSomeText() {
    return someText;
  }

  public void setSomeText(String someText) {
    this.someText = someText;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
