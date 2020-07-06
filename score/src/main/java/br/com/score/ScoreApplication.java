package br.com.score;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ScoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoreApplication.class, args);
	}

}
