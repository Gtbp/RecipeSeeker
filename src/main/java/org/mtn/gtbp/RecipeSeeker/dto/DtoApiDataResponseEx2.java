package org.mtn.gtbp.RecipeSeeker.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class DtoApiDataResponseEx2 extends DtoApiDataResponse{

	private List<DtoRecipe> recipes;
	
}
