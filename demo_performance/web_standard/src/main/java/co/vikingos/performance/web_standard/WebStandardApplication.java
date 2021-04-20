package co.vikingos.performance.web_standard;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableMongock
@SpringBootApplication
public class WebStandardApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebStandardApplication.class, args);
	}

}
