package org.mtn.gtbp.RecipeSeeker.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DtoTotalNutrients {

	private String label;
	private Double quantity;
	private String unit;
	
	
	//CONSTRUCTOR
	public DtoTotalNutrients(String label, Double quantity, String unit) {
		super();
		this.label = label;
		this.quantity = quantity;
		this.unit = unit;
	}
	
	
}
