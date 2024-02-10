package org.mtn.gtbp.RecipeSeeker.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@DiscriminatorValue(value = "Cholesterol")
public class Cholesterol extends TotalNutrients {

	private Long idCholesterol;
	
	public Cholesterol(String label, Double quantity, String unit, Recipe recipe) {
		super(label, quantity, unit, recipe);
	}
	
	public String toString() {
		return "Cholesterol [" + super.toString() + "]";
	}

}
