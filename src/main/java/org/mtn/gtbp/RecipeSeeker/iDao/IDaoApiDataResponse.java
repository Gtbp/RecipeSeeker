package org.mtn.gtbp.RecipeSeeker.iDao;

import org.mtn.gtbp.RecipeSeeker.entities.ApiDataResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDaoApiDataResponse extends JpaRepository<ApiDataResponse, Long>{

	ApiDataResponse findByQ(String q); // Recipe list as a function of q parameter
	
}
