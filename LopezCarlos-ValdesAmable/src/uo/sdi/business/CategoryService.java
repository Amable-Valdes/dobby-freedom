package uo.sdi.business;

import java.util.List;

import uo.sdi.business.util.BusinessException;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.UserDTO;

public interface CategoryService {

	//CRUD
	List<CategoryDTO> findCategoriesByUser(UserDTO userDTO) 
			throws BusinessException;
}
