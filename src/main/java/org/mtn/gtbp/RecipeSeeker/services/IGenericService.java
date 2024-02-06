package org.mtn.gtbp.RecipeSeeker.services;

import java.util.List;

public interface IGenericService<E,ID,DTO> {

	public E searchById(ID id);
	public E saveOrUpdate(E entity);
	public List<E> searchAll();
	
    public void deleteById(ID id); //throws NotFoundException;
    public boolean existById(ID id);
    
    public DTO searchDtoById(ID id); //throws NotFoundException;
    public List<DTO> searchAllDto();
}
