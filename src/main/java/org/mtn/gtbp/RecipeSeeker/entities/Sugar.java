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
@DiscriminatorValue(value = "Sugar")
public class Sugar extends TotalNutrients {

	private Long idSugar;

	public Sugar(String label, Double quantity, String unit, Recipe recipe) {
		super(label, quantity, unit, recipe);
	}

	public String toString() {
		return "Sugar [" + super.toString() + "]";
	}
	
}
