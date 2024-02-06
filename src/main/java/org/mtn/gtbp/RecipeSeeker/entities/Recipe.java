package org.mtn.gtbp.RecipeSeeker.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Recipe obtained when user type "q" from Ingredients Class*
 * source: reference of recipe
 * healthLabels: food preferences
 * ingredientLines: text of ingredients
 */

@Entity
@Getter @Setter @NoArgsConstructor
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRecipe;
	
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
	
	@ManyToOne // Many Recipe To One ApiDataResponse
	@JoinColumn(name= "idResponse")
	private ApiDataResponse apidataresponse;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy= "recipe", cascade = CascadeType.ALL) // One Recipe To Many Ingredients
	@JsonIgnore
	private List<Ingredients> ingredients;
	
	@OneToOne(mappedBy = "recipe") // One Recipe To One TotalNutrients
	private TotalNutrients totalNutrients;

	
	//CONSTRUCTOR
	public Recipe(Long idRecipe, String uri, String label, String image, String source, String url,
			List<String> healthLabels, List<String> cautions, List<String> ingredientLines, Double calories,
			Double totalWeight, Double totalTime, String cuisineType, List<String> mealType, ApiDataResponse apidataresponse) {
		super();
		this.idRecipe = idRecipe;
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
		this.apidataresponse = apidataresponse;
	}
	
	
	
	public Recipe(Long idRecipe, String uri, String label, String image, String source, String url) {
		super();
		this.idRecipe = idRecipe;
		this.uri = uri;
		this.label = label;
		this.image = image;
		this.source = source;
		this.url = url;
	}

	//TO STRING
	@Override
	public String toString() {
		return "Recipe [idRecipe=" + idRecipe + ", uri=" + uri + ", label=" + label + ", image=" + image + ", source="
				+ source + ", url=" + url + ", healthLabels=" + healthLabels + ", cautions=" + cautions
				+ ", ingredientLines=" + ingredientLines + ", calories=" + calories + ", totalWeight=" + totalWeight
				+ ", totalTime=" + totalTime + ", cuisineType=" + cuisineType + ", mealType=" + mealType
				+ ", apidataresponse=" + apidataresponse + "]";
	}

}
