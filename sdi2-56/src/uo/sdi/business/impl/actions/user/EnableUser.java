package uo.sdi.business.impl.actions.user;

import uo.sdi.business.util.Asserts;
import uo.sdi.business.util.BusinessException;
import uo.sdi.business.util.Command;
import uo.sdi.dto.UserDTO;
import uo.sdi.model.User;
import uo.sdi.model.types.UserStatus;
import uo.sdi.persistence.UserFinder;
import uo.sdi.persistence.util.Jpa;

/**
 * Este Action nos permite cambiar el estado de un usuario de disable a enable.
 * 
 * @author Amable y Carlos
 *
 */
public class EnableUser implements Command {

	private UserDTO userDTO;

	public EnableUser(UserDTO user) {
		this.userDTO = user;
	}

	@Override
	public Object execute() throws BusinessException {
		//Buscamos el usuario
		User user = UserFinder.findByLogin(userDTO.getLogin());
		//Comprobamos que existe de verdad
		Asserts.assertUserExist(user);
		//Comprobamos que su estado sea disabled
		Asserts.assertUserIsBlocked(user);
		//Modificamos su estado
		user.setStatus(UserStatus.ENABLED);
		Jpa.getManager().merge(user);
		return null;
	}
}
