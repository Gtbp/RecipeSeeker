	package org.mtn.gtbp.RecipeSeeker.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Ingredients list which compose recipes
 * text: composition and use of ingredient
 */

@Entity
@Getter @Setter @NoArgsConstructor
public class Ingredients {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idIngredients;
	
	private String text;
	private Double quantity;
	private String measure;
	private String food;
	private Double weight;
	private String foodCategory;
	private String foodId;
	private String image;
	
	@ManyToOne // Many Ingredients To One Recipe
	@JoinColumn(name= "idRecipe")
	private Recipe recipe;

	
	//CONSTRUCTOR
	public Ingredients(Long idIngredients, String text, Double quantity, String measure, String food, Double weight,
			String foodCategory, String foodId, String image, Recipe recipe) {
		super();
		this.idIngredients = idIngredients;
		this.text = text;
		this.quantity = quantity;
		this.measure = measure;
		this.food = food;
		this.weight = weight;
		this.foodCategory = foodCategory;
		this.foodId = foodId;
		this.image = image;
		this.recipe = recipe;
	}

	
	//TO STRING
	@Override
	public String toString() {
		return "Ingredients [idIngredients=" + idIngredients + ", text=" + text + ", quantity=" + quantity
				+ ", measure=" + measure + ", food=" + food + ", weight=" + weight + ", foodCategory=" + foodCategory
				+ ", foodId=" + foodId + ", image=" + image + ", recipe=" + recipe + "]";
	}	
}
