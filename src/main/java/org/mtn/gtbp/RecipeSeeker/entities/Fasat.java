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
@DiscriminatorValue(value = "Fasat")
public class Fasat extends TotalNutrients {

	private Long idFasat;
	
	public Fasat(String label, Double quantity, String unit, Recipe recipe) {
		super(label, quantity, unit, recipe);
	}
	
	public String toString() {
		return "Fasat [" + super.toString() + "]";
	}

}
