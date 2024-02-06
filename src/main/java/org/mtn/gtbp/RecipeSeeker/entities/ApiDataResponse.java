package org.mtn.gtbp.RecipeSeeker.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class supply API response q: composition of found recipe typed by user
 * count: contains the total number of search results found hits: list of
 * recipes from response
 */

@Entity
@Getter @Setter @NoArgsConstructor
public class ApiDataResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idResponse;

	private String q;
	private Integer count;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy= "apiDataResponse", cascade = CascadeType.ALL) // One ApiDataResponse To Many Recipe
	@JsonIgnore
	private List<Recipe> recipes;

	
	// CONSTRUCTOR
	public ApiDataResponse(Long idResponse, String q, Integer count) {
		super();
		this.idResponse = idResponse;
		this.q = q;
		this.count = count;
	}


	//TO STRING
	@Override
	public String toString() {
		return "ApiDataResponse [idResponse=" + idResponse + ", q=" + q + ", count=" + count + "]";
	}

}
