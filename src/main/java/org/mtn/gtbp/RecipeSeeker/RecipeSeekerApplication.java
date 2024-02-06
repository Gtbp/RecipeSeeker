package org.mtn.gtbp.RecipeSeeker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeSeekerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeSeekerApplication.class, args);
		System.out.println("http://localhost:8080/RecipeSeeker");
	}

}
