package uo.sdi.business.impl.actions.user;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.model.Category;
import uo.sdi.model.Task;
import uo.sdi.model.User;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

/**
 * Este Action nos permite permite borrar un nuevo usuario del sistema.
 * 
 * @author Amable y Carlos
 *
 */
public class RemoveUser implements Command{
	
	private String login;
	
	public RemoveUser(String login){
		this.login = login;
	}

	@Override
	public Object execute() throws BusinessException {
		//Buscamos el usuario por su login
		User user = UserFinder.findByLogin(login);
		//¿El usuario existe?
		Asserts.assertUserExist(user);
		// Borramos sus tareas
		for (Task task : user.getTasks()) {
				Jpa.getManager().remove(task);
		}
		//Borramos sus categorias
		for( Category category : user.getCategories() ){
			Jpa.getManager().remove(category);
		}
		//Borramos el usuario
		Jpa.getManager().remove(user);
		return null;
	}
}
