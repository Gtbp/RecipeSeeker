package org.mtn.gtbp.RecipeSeeker.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
	
	@Column(columnDefinition = "TEXT")
	private String image;
	private String source;
	private String url;
	
	@ElementCollection
	@CollectionTable(name="recipe_healthLabels", joinColumns= @JoinColumn(name = "idRecipe"))
	@Column(name = "healthLabels")
	private List<String> healthLabels;
	
	@ElementCollection
	@CollectionTable(name="recipe_cautions", joinColumns= @JoinColumn(name = "idRecipe"))
	@Column(name = "caution")
	private List<String> cautions;
	
	@ElementCollection
	@CollectionTable(name="recipe_ingredientLines", joinColumns= @JoinColumn(name = "idRecipe"))
	@Column(name = "ingredientLines")
	private List<String> ingredientLines;
	
	private Double calories;
	private Double totalWeight;
	private Double totalTime;
	private String cuisineType;
	
	@ElementCollection
	@CollectionTable(name="recipe_mealType", joinColumns= @JoinColumn(name = "idRecipe"))
	@Column(name = "mealType")
	private List<String> mealType;
	
	@ManyToOne // Many Recipe To One ApiDataResponse
	@JoinColumn(name= "idResponse")
	private ApiDataResponse apidataresponse;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy= "recipe", cascade = CascadeType.ALL) // One Recipe To Many Ingredients
	@JsonIgnore
	private List<Ingredients> ingredients;
	
	@OneToOne // One Recipe To One TotalNutrients
	@JoinColumn(name="idTotalNutrients")
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
	
	public Recipe(Long idRecipe, String uri, String label, String source, String url, List<String> healthLabels,
			List<String> cautions, List<String> ingredientLines, Double calories, Double totalWeight, Double totalTime,
			String cuisineType, List<String> mealType, ApiDataResponse apidataresponse) {
		super();
		this.idRecipe = idRecipe;
		this.uri = uri;
		this.label = label;
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
