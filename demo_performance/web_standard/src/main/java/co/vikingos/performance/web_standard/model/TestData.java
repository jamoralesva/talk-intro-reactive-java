package co.vikingos.performance.web_standard.model;


import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("testdata")
public class TestData {
  @Id
  private String id;
  @Field("name")
  @Indexed(unique = true)
  private String name;
  @Field("age")
  private BigDecimal age;
}
