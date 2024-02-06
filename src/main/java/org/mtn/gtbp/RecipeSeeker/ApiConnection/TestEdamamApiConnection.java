package org.mtn.gtbp.RecipeSeeker.ApiConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.mtn.gtbp.RecipeSeeker.entities.ApiDataResponse;
import org.mtn.gtbp.RecipeSeeker.entities.Cholesterol;
import org.mtn.gtbp.RecipeSeeker.entities.EnergyKcal;
import org.mtn.gtbp.RecipeSeeker.entities.Fasat;
import org.mtn.gtbp.RecipeSeeker.entities.Ingredients;
import org.mtn.gtbp.RecipeSeeker.entities.Recipe;
import org.mtn.gtbp.RecipeSeeker.entities.Sugar;
import org.mtn.gtbp.RecipeSeeker.entities.Water;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestEdamamApiConnection {

	private static String apiKey;
	private static String appId;

	private final static String apiUrlTemplate = "https://api.edamam.com/search?q={query}&app_id={appId}&app_key={apiKey}";
	private static ApiDataResponse apiDataResponse;
	private static Recipe recipeEntity;

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		//getDataConfigProperties();
		//fetchApiEdamam("chicken potatoes apple");
		testSaveBDD("chicken potatoes apple");
	}

	public static Map<String, String> getDataConfigProperties() {
		Properties properties = new Properties();
		FileInputStream input;
        Map<String, String> apiCredentials = new HashMap<>();

		try {
			input = new FileInputStream("src/main/resources/config.properties");
			try {
				properties.load(input);
			} catch (IOException e) {
				e.printStackTrace();
			}
			apiKey = properties.getProperty("api_key");
			appId = properties.getProperty("app_id");
			
			System.out.println("api_key=" + apiKey);
			System.out.println("app_id=" + appId);
			
			apiCredentials.put("apiKey", apiKey);
			apiCredentials.put("appId", appId);
			
		} catch (FileNotFoundException e) {
			System.out.println("Config.properties doesn't exist");
			e.printStackTrace();
		}
		return apiCredentials; 
	}

	public static String fetchApiEdamam(String query) {
        Map<String, String> credentials = getDataConfigProperties();
        
        apiKey = credentials.get("apiKey");
        appId = credentials.get("appId");

		// URL creation with dynamic parameter (query)
		String apiUrl = apiUrlTemplate.replace("{query}", query)
					.replace("{appId}", appId)
					.replace("{apiKey}", apiKey);

		// Call to Api
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", apiKey);

		HttpEntity<String> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

		// Response Api treatment
		String responseBody = response.getBody();
		return responseBody;
	}

	public static void testSaveBDD(String query) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(fetchApiEdamam(query));

		// Fill ApiDataResponse table
		String q = jsonNode.path("q").asText();
		Integer count = jsonNode.path("count").asInt();
		ApiDataResponse apiDataResponse = new ApiDataResponse(null, q, count);
		
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
					calories, totalWeight, totalTime, cuisineType, null, apiDataResponse);

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
			}

			JsonNode TotalNutrientsNode = recipe.path("totalNutrients");

			// Energy_kcal
			JsonNode energy_Kcal = TotalNutrientsNode.path("ENERC_KCAL");
			String labelKcal = energy_Kcal.path("label").asText();
			Double quantity = energy_Kcal.path("quantity").asDouble();
			String unit = energy_Kcal.path("unit").asText();
			EnergyKcal energyKcalEntity = new EnergyKcal(labelKcal, quantity, unit, recipeEntity);
			System.out.println(energyKcalEntity);

			// Cholesterol
			JsonNode chole = TotalNutrientsNode.path("CHOLE");
			String labelChol = chole.path("label").asText();
			Double quantityChol = chole.path("quantity").asDouble();
			String unitChol = chole.path("unit").asText();
			Cholesterol cholesterolEntity = new Cholesterol(labelChol, quantityChol, unitChol, recipeEntity);

			// Water
			JsonNode water = TotalNutrientsNode.path("WATER");
			String labelWat = water.path("label").asText();
			Double quantityWat = water.path("quantity").asDouble();
			String unitWat = water.path("unit").asText();
			Water waterEntity = new Water(labelWat, quantityWat, unitWat, recipeEntity);

			// Fasat
			JsonNode fasat = TotalNutrientsNode.path("FASAT");
			String labelFas = fasat.path("label").asText();
			Double quantityFas = fasat.path("quantity").asDouble();
			String unitFas = fasat.path("unit").asText();
			Fasat fasatEntity = new Fasat(labelFas, quantityFas, unitFas, recipeEntity);

			// Sugar
			JsonNode sugar = TotalNutrientsNode.path("SUGAR");
			String labelSug = sugar.path("label").asText();
			Double quantitySug = sugar.path("quantity").asDouble();
			String unitSug = sugar.path("unit").asText();
			Sugar sugarEntity = new Sugar(labelSug, quantitySug, unitSug, recipeEntity);

		}
	}
}
