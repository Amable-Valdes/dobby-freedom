package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.CategoryService;
import uo.sdi.business.impl.actions.category.ListCategoriesByUser;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.CommandExecutor;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.UserDTO;

public class CategoryServiceImpl implements CategoryService {
	
	private CommandExecutor executor = new CommandExecutor();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryDTO> findCategoriesByUser(UserDTO userDTO) 
			throws BusinessException {
		return (List<CategoryDTO>) executor.execute(
				new ListCategoriesByUser(userDTO));
	}

}
