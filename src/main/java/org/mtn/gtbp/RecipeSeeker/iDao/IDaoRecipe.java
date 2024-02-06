package org.mtn.gtbp.RecipeSeeker.iDao;

import java.util.List;

import org.mtn.gtbp.RecipeSeeker.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDaoRecipe extends JpaRepository<Recipe, Long>{

	List<Recipe> findByLabel(String label); // Recipe list as a function of label
	List<Recipe> findByTotalTime(Double totalTime); // Recipe list as a function of totaltime
	List<Recipe> findByMealType(String mealType); // Recipe list as a function of mealtype
	List<Recipe> findByCuisineType(String cuisineType); // Recipe list as a function of cuisinetype
	
}
