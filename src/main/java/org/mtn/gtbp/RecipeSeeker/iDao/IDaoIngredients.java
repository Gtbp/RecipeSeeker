package org.mtn.gtbp.RecipeSeeker.iDao;

import org.mtn.gtbp.RecipeSeeker.entities.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDaoIngredients extends JpaRepository<Ingredients, Long>{

	
}
