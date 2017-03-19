package uo.sdi.business.impl.actions.category;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.util.Cloner;
import uo.sdi.model.Category;
import uo.sdi.model.User;
import uo.sdi.persistence.CategoryFinder;
import uo.sdi.persistence.UserFinder;

public class FindCategory implements Command {
	
	private String login;
	private String name;

	public FindCategory(String login, String name) {
		this.login = login;
		this.name = name;
	}

	@Override
	public Object execute() throws BusinessException {
		//Buscamos el usuario y comprobamos que existe
		User user = UserFinder.findByLogin(login);
		Asserts.assertUserExist(user);
		//Buscamos la categoria y comprobamos que existe
		Category category = CategoryFinder.findByUserAndName(user.getId(), 
				name);
		Asserts.assertCategoryExist(category);
		//Devolvemos la categoria
		return Cloner.clone(category);
	}
}
