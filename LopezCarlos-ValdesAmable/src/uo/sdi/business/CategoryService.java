package uo.sdi.business;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.CategoryDTO;

public interface CategoryService {

	//CRUD
	public void addCategory() throws BusinessException;
	public CategoryDTO findCategory() throws BusinessException;
	public void updateCategory() throws BusinessException;
	public void removeCategory() throws BusinessException;
}
