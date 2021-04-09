package co.vikingos.performance.web_reactive.config;

import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@EnableReactiveCassandraRepositories
public class CassandraConfig extends AbstractReactiveCassandraConfiguration {

  @Override
  protected String getKeyspaceName() {
    return "someKeySpace";
  }

  @Override
  public SchemaAction getSchemaAction() {
    return SchemaAction.NONE;
  }
}