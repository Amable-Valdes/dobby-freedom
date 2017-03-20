package uo.sdi.business.impl.actions.user;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.business.util.Encriptator;
import uo.sdi.dto.util.Cloner;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.UserFinder;

/**
 * Este Action nos permite loguearnos en el sistema.
 * 
 * @author Amable y Carlos
 *
 */
public class LoginUser implements Command {
	
	private String login;
	private String password;

	public LoginUser(String login, String password) {
		this.login = login;
		this.password = password;
	}

	@Override
	public Object execute() throws BusinessException {
		//Buscamos al usuario.
		User user = UserFinder.findByLogin(login);
		//Comprobamos que el usuario existe.
		Asserts.assertUserExist(user);
		//Si el usuario esta Enabled y las contraseñas coinciden, se loguea.
		if(user.getStatus() == UserStatus.ENABLED && 
				user.getPassword().equals(Encriptator.encrypt(password))){
			return Cloner.clone(user);
		}
		throw new BusinessException("Password incorrecta o usuario bloqueado");
	}
}
