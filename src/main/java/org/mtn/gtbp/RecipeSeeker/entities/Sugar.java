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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSugar;

	//CONSTRUCTOR
	public Sugar(String label, Double quantity, String unit, Recipe recipe) {
		super(label, quantity, unit, recipe);
	}

	//TO STRING
	public String toString() {
		return "Sugar [" + super.toString() + "]";
	}
	
	
	
}
