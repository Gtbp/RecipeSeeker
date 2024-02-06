package org.mtn.gtbp.RecipeSeeker.services;

import java.util.List;

import org.mtn.gtbp.RecipeSeeker.converter.GenericConverter;
import org.springframework.data.repository.CrudRepository;

public abstract class AbstractGenericService<E, ID, DTO> implements IGenericService<E, ID, DTO> {

	public abstract Class<DTO> getDtoClass();

	public abstract CrudRepository<E, ID> getDao();

	@Override
	public E searchById(ID id) {
		return getDao().findById(id).orElse(null);
	}

	@Override
	public E saveOrUpdate(E entity) {
		return getDao().save(entity);
	}

	@Override
	public List<E> searchAll() {
		return (List<E>)getDao().findAll();
	}

	@Override
	public void deleteById(ID id) {
		if(!(getDao().existsById(id)))
			//throw new NotFoundException("no entity to delete for id: "+id);
		getDao().deleteById(id);
	}

	@Override
	public boolean existById(ID id) {
		return getDao().existsById(id);
	}

	@Override
	public DTO searchDtoById(ID id) { 
		E e = this.searchById(id);
		if(e != null) {
			return GenericConverter.map(e,getDtoClass());
		}
		return null; //throw new NotFoundException("Entity not found for id "+id);
	}

	@Override
	public List<DTO> searchAllDto() {
		return GenericConverter.map(this.searchAll(),getDtoClass());
	}

}
