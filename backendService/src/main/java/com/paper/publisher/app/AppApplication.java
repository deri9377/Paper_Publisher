
package com.paper.publisher.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.paper.publisher.app.db.PaperFactory;
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
public class AppApplication {



	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
		PaperFactory paperFactory = new PaperFactory();
		paperFactory.createPapers();
	}

}

