package org.mtn.gtbp.RecipeSeeker.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DtoIngredients {

	private String text;
	private Double quantity;
	private String measure;
	private String food;
	private Double weight;
	private String foodCategory;
	private String foodId;
	private String image;
	
	
	//CONSTRUCTOR
	public DtoIngredients(String text, Double quantity, String measure, String food, Double weight, String foodCategory,
			String foodId, String image) {
		super();
		this.text = text;
		this.quantity = quantity;
		this.measure = measure;
		this.food = food;
		this.weight = weight;
		this.foodCategory = foodCategory;
		this.foodId = foodId;
		this.image = image;
	}
	
}
