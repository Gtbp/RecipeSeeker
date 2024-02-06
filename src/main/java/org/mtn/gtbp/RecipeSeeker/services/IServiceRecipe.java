package org.mtn.gtbp.RecipeSeeker.services;

import java.util.List;

import org.mtn.gtbp.RecipeSeeker.dto.DtoRecipe;
import org.mtn.gtbp.RecipeSeeker.dto.DtoRecipeEx1;
import org.mtn.gtbp.RecipeSeeker.entities.Recipe;

public interface IServiceRecipe extends IGenericService<Recipe,Long,DtoRecipe>{
	
	/**
	 * DtoRecipeEx1 : DTO Level 1 including q (composition of found recipe typed by user),
	 * Recipe list, idIngredients, idNutrients
	 */
	
	public List<DtoRecipeEx1> searchByLabelWithQ(String label);
	public List<DtoRecipeEx1> searchByTotalTimeWithQ(Double totalTime);
	public List<DtoRecipeEx1> searchByMealTypeWithQ(String mealType);
	public List<DtoRecipeEx1> searchByCuisineTypeWithQ(String cuisineType);
	public DtoRecipeEx1 saveOrUpdate(DtoRecipeEx1 dtoRecipeEx1);
	
}
