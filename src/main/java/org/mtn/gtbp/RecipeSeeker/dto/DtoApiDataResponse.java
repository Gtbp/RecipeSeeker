package org.mtn.gtbp.RecipeSeeker.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DtoApiDataResponse {
	
	private String q;
	private Integer count;
	
	
	//CONSTRUCTOR
	public DtoApiDataResponse(String q, Integer count) {
		super();
		this.q = q;
		this.count = count;
	}	

}
