package org.mtn.gtbp.RecipeSeeker.entities;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Relation One to One avec Recipe
 */

@Entity
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="NutrientType" , discriminatorType = DiscriminatorType.STRING)
public class TotalNutrients {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTotalNutrients;
	private String label;
	private Double quantity;
	private String unit;
	
	@OneToOne(optional=false) // One TotalNutrients To One Recipe
	@JoinColumn(name="idRecipe")
	private Recipe recipe;

	
	//CONSTRUCTOR
	public TotalNutrients(String label, Double quantity, String unit, Recipe recipe) {
		super();
		this.label = label;
		this.quantity = quantity;
		this.unit = unit;
		this.recipe = recipe;
	}
	public TotalNutrients(String label, Double quantity, String unit) {
		super();
		this.label = label;
		this.quantity = quantity;
		this.unit = unit;
	}


	//TO STRING
	@Override
	public String toString() {
		return "TotalNutrients [label=" + label + ", quantity=" + quantity + ", unit=" + unit + ", recipe=" + recipe
				+ "]";
	}
	
}
