package org.mtn.gtbp.RecipeSeeker.services;

import java.util.ArrayList;
import java.util.List;

import org.mtn.gtbp.RecipeSeeker.ApiConnection.EdamamApiConnection;
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
import org.mtn.gtbp.RecipeSeeker.iDao.IDaoApiDataResponse;
import org.mtn.gtbp.RecipeSeeker.iDao.IDaoCholesterol;
import org.mtn.gtbp.RecipeSeeker.iDao.IDaoEnergyKcal;
import org.mtn.gtbp.RecipeSeeker.iDao.IDaoFasat;
import org.mtn.gtbp.RecipeSeeker.iDao.IDaoIngredients;
import org.mtn.gtbp.RecipeSeeker.iDao.IDaoRecipe;
import org.mtn.gtbp.RecipeSeeker.iDao.IDaoSugar;
import org.mtn.gtbp.RecipeSeeker.iDao.IDaoWater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceApiDataResponseImpl extends AbstractGenericService<ApiDataResponse, Long, DtoApiDataResponse>
		implements IServiceApiDataResponse {

	//private static Recipe recipeEntity;

	@Autowired
	private IDaoApiDataResponse iDaoApiDataResponse; // Principal DAO
	@Autowired
	private IDaoRecipe iDaoRecipe;
	@Autowired
	private IDaoIngredients iDaoIngredients;
	@Autowired
	private IDaoWater iDaoWater;
	@Autowired
	private IDaoCholesterol iDaoCholesterol;
	@Autowired
	private IDaoEnergyKcal iDaoEnergyKcal;
	@Autowired
	private IDaoFasat iDaoFasat;
	@Autowired
	private IDaoSugar iDaoSugar;
	

	@Override
	public Class<DtoApiDataResponse> getDtoClass() {
		return DtoApiDataResponse.class;
	}

	@Override
	public CrudRepository<ApiDataResponse, Long> getDao() {
		return this.iDaoApiDataResponse;
	}
	
	public void saveAllEntities(ApiDataResponse apiDataResponseEntity, Recipe recipeEntity, Ingredients ingredientsEntity, 
			Water waterEntity, Cholesterol cholesterolEntity, EnergyKcal energyKcalEntity, Fasat fasatEntity, Sugar sugarEntity, 
			String query) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		EdamamApiConnection edamamApiConnection = new EdamamApiConnection();
		
		JsonNode jsonNode = objectMapper.readTree(edamamApiConnection.fetchApiEdamam(query));

		// Fill ApiDataResponse table
		String q = jsonNode.path("q").asText();
		Integer count = jsonNode.path("count").asInt();
		apiDataResponseEntity = new ApiDataResponse(null, q, count);
		
		// Save apiDataResponseEntity
		iDaoApiDataResponse.save(apiDataResponseEntity);
		
		// Fill Recipe table
		for (JsonNode hit : jsonNode.path("hits")) {
			JsonNode recipe = hit.path("recipe");
			String uri = recipe.path("uri").asText();
			String label = recipe.path("label").asText();
			String image = recipe.path("image").asText();
			String source = recipe.path("source").asText();
			String url = recipe.path("url").asText();
			String cuisineType = recipe.path("cuisineType").asText();
			Double calories = recipe.path("calories").asDouble();
			Double totalWeight = recipe.path("totalWeight").asDouble();
			Double totalTime = recipe.path("totalTime").asDouble();
			JsonNode healthLabelsNode = recipe.path("healthLabels");
			List<String> healthLabels = new ArrayList<>();
			if (healthLabelsNode.isArray()) {
				for (JsonNode labelNode : healthLabelsNode) {
					healthLabels.add(labelNode.asText());
				}
			}
			JsonNode cautionNode = recipe.path("cautions");
			List<String> cautions = new ArrayList<>();
			if (cautionNode.isArray()) {
				for (JsonNode cautionsNode : cautionNode) {
					cautions.add(cautionsNode.asText());
				}
			}
			JsonNode mealTypeNode = recipe.path("mealType");
			List<String> mealType = new ArrayList<>();
			if (mealTypeNode.isArray()) {
				for (JsonNode mealTypesNode : mealTypeNode) {
					mealType.add(mealTypesNode.asText());
				}
			}
			JsonNode ingredientLinesNode = recipe.path("ingredientLines");
			List<String> ingredientLines = new ArrayList<>();
			if (ingredientLinesNode.isArray()) {
				for (JsonNode ingredientsLinesNode : ingredientLinesNode) {
					ingredientLines.add(ingredientsLinesNode.asText());
				}
			}
			
			recipeEntity = new Recipe(null, uri, label, image, source, url, healthLabels, cautions, ingredientLines,
					calories, totalWeight, totalTime, cuisineType, null, apiDataResponseEntity);
			
			iDaoRecipe.save(recipeEntity);

			// Fill Ingredients
			for (JsonNode ingredients : recipe.path("ingredients")) {

				String text = ingredients.path("text").asText();
				Double quantity = ingredients.path("quantity").asDouble();
				String measure = ingredients.path("measure").asText();
				String food = ingredients.path("food").asText();
				Double weight = ingredients.path("weight").asDouble();
				String foodCategory = ingredients.path("foodCategory").asText();
				String foodId = ingredients.path("foodId").asText();
				String imageIng = ingredients.path("image").asText();

				Ingredients ingredientEntity = new Ingredients(null, text, quantity, measure, food, weight,
						foodCategory, foodId, imageIng, recipeEntity);
				
				iDaoIngredients.save(ingredientEntity);
			}

			JsonNode TotalNutrientsNode = recipe.path("totalNutrients");

			JsonNode energy_Kcal = TotalNutrientsNode.path("ENERC_KCAL");
			String labelKcal = energy_Kcal.path("label").asText();
			Double quantity = energy_Kcal.path("quantity").asDouble();
			String unit = energy_Kcal.path("unit").asText();
			energyKcalEntity = new EnergyKcal(labelKcal, quantity, unit, recipeEntity);
			
			iDaoEnergyKcal.save(energyKcalEntity);
			
			JsonNode chole = TotalNutrientsNode.path("CHOLE");
			String labelChol = chole.path("label").asText();
			Double quantityChol = chole.path("quantity").asDouble();
			String unitChol = chole.path("unit").asText();
			cholesterolEntity = new Cholesterol(labelChol, quantityChol, unitChol, recipeEntity);
			
			iDaoCholesterol.save(cholesterolEntity);
			
			JsonNode water = TotalNutrientsNode.path("WATER");
			String labelWat = water.path("label").asText();
			Double quantityWat = water.path("quantity").asDouble();
			String unitWat = water.path("unit").asText();
			waterEntity = new Water(labelWat, quantityWat, unitWat, recipeEntity);
			
			iDaoWater.save(waterEntity);

			JsonNode fasat = TotalNutrientsNode.path("FASAT");
			String labelFas = fasat.path("label").asText();
			Double quantityFas = fasat.path("quantity").asDouble();
			String unitFas = fasat.path("unit").asText();
			fasatEntity = new Fasat(labelFas, quantityFas, unitFas, recipeEntity);

			iDaoFasat.save(fasatEntity);
			
			JsonNode sugar = TotalNutrientsNode.path("SUGAR");
			String labelSug = sugar.path("label").asText();
			Double quantitySug = sugar.path("quantity").asDouble();
			String unitSug = sugar.path("unit").asText();
			sugarEntity = new Sugar(labelSug, quantitySug, unitSug, recipeEntity);
			
			iDaoSugar.save(sugarEntity);

		}
	}

	@Override
	public DtoApiDataResponseEx1 saveOrUpdateDto(DtoApiDataResponseEx1 dtoApiDataResponseEx1) {
		return null;
	}

	@Override
	public DtoApiDataResponseEx2 searchByQWithRecipe(String q) {
		return null;
	}

}
