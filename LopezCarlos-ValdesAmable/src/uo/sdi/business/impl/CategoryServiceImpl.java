package uo.sdi.business.impl;

import java.util.List;

import uo.sdi.business.CategoryService;
import uo.sdi.business.impl.actions.category.FindCategory;
import uo.sdi.business.impl.actions.category.ListCategoriesByUser;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.CommandExecutor;
import uo.sdi.dto.CategoryDTO;

public class CategoryServiceImpl implements CategoryService {
	
	private CommandExecutor executor = new CommandExecutor();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryDTO> findCategoriesByUser(String login) 
			throws BusinessException {
		return (List<CategoryDTO>) executor.execute(
				new ListCategoriesByUser(login));
	}
	
	@Override
	public CategoryDTO findCategoryByUserAndCategoryName(String login, 
			String name) throws BusinessException {
		return (CategoryDTO) executor.execute(new FindCategory(login, name));
	}

}
