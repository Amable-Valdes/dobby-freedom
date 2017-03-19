package uo.sdi.business.impl.actions.user;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.util.Cloner;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;

public class FindUser implements Command {
	
	private String login;

	public FindUser(String login) {
		this.login = login;
	}

	@Override
	public Object execute() throws BusinessException {
		//Buscamos al usuario.
		User user = UserFinder.findByLogin(login);
		//Comprobamos que existe
		Asserts.assertUserExist(user);
		return Cloner.clone(user);
	}

}
