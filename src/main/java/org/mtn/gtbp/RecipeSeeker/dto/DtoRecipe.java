package org.mtn.gtbp.RecipeSeeker.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DtoRecipe {

	private String uri;
	private String label;
	private String image;
	private String source;
	private String url;
	private List<String> healthLabels;
	private List<String> cautions;
	private List<String> ingredientLines;
	private Double calories;
	private Double totalWeight;
	private Double totalTime;
	private String cuisineType;
	private List<String> mealType;
	

	//CONSTRUCTOR
	public DtoRecipe(String uri, String label, String image, String source, String url, List<String> healthLabels,
			List<String> cautions, List<String> ingredientLines, Double calories, Double totalWeight, Double totalTime,
			String cuisineType, List<String> mealType) {
		super();
		this.uri = uri;
		this.label = label;
		this.image = image;
		this.source = source;
		this.url = url;
		this.healthLabels = healthLabels;
		this.cautions = cautions;
		this.ingredientLines = ingredientLines;
		this.calories = calories;
		this.totalWeight = totalWeight;
		this.totalTime = totalTime;
		this.cuisineType = cuisineType;
		this.mealType = mealType;
	}
	
}
