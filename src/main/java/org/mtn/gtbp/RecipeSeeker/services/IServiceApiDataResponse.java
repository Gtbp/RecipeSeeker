package org.mtn.gtbp.RecipeSeeker.services;

import org.mtn.gtbp.RecipeSeeker.dto.DtoApiDataResponse;
import org.mtn.gtbp.RecipeSeeker.dto.DtoApiDataResponseEx1;
import org.mtn.gtbp.RecipeSeeker.dto.DtoApiDataResponseEx2;
import org.mtn.gtbp.RecipeSeeker.entities.ApiDataResponse;
import org.mtn.gtbp.RecipeSeeker.entities.Cholesterol;
import org.mtn.gtbp.RecipeSeeker.entities.EnergyKcal;
import org.mtn.gtbp.RecipeSeeker.entities.Fasat;
import org.mtn.gtbp.RecipeSeeker.entities.Ingredients;
import org.mtn.gtbp.RecipeSeeker.entities.Recipe;
import org.mtn.gtbp.RecipeSeeker.entities.Sugar;
import org.mtn.gtbp.RecipeSeeker.entities.Water;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IServiceApiDataResponse extends IGenericService<ApiDataResponse,Long,DtoApiDataResponse>{

	/**
	 * DtoApiDataResponseEx1 : DTO Level 1 including idRecipe
	 * DtoApiDataResponseEx2 : DTO Level 2 including Recipe Class
	 * @return
	 */
	
	public DtoApiDataResponseEx1 saveOrUpdateDto(DtoApiDataResponseEx1 dtoApiDataResponseEx1);
	public DtoApiDataResponseEx2 searchByQWithRecipe(String q);
	public void saveAllEntities(ApiDataResponse apiDataResponseEntity, Recipe recipeEntity, Ingredients ingredientsEntity, 
			Water waterEntity, Cholesterol cholesterolEntity, EnergyKcal energyKcalEntity, Fasat fasatEntity, Sugar sugarEntity, 
			String query) throws JsonMappingException, JsonProcessingException;
}
